package production.Factories.Produktion.Dronefactory;

import BuildingExtensions.DroneProducerExt;
import ImportandEnums.DroneTypes;
import lombok.Getter;
import management.ManagementSystems.DroneManagement;
import ImportandEnums.BuildingTypes;
import production.Factories.Building;
import production.Factories.BuildingInformationElement;
import production.Factories.Connector.DirectResourceCon;
import production.Factories.Connector.InternalStorage;
import specificexceptions.*;

/**
 * Dronefactory - Produziert Dronen aller art.
 * <p>
 * ID: 1
 */
public class DroneFactory extends Building<DronefactoryDataEntity> {
    private static int cc = -1;

    public DroneFactory() {
        super(BuildingTypes.DRONEFACTORY);
        cc++;
        id = cc;

        dataEntity = new DronefactoryDataEntity(buildingType);
    }

    /**
     * Jeweils ein Zyclus beudeutet eine Runde
     * Die Fabrik benoetigt eine gewisse anzahl an Runden um die entschprechende Drone zu Produzieren
     * Die Fabrik verbraucht pro Runde jeweils einen gewissen betrag an Energie
     * Ist keine Energie mehr vorhanden, wird nicht weitergearbeitet
     */
    @Override
    protected void updateBuilding() throws NotEnoughResourceException, NotEnoughEnergyException {
        if (dataEntity.isProducing() && dataEntity.hasEnergy()) {
            dataEntity.useEnergy();
            dataEntity.factorise();
            finishDrone();
        }
        if (dataEntity.prodIsActive()) {
            droneExtension();
        }
    }

    public void addDroneProducerExtension(DroneTypes drone) {
        if (!dataEntity.inConstruction()) {
            dataEntity.setProd(new DroneProducerExt(drone));
        }
    }

    private void droneExtension() throws NotEnoughResourceException {
        if (dataEntity.prodHasAcces()) {
            if (!dataEntity.isProducing() && !dataEntity.getProd().isFull()) {
                startProductionAutomatic(dataEntity.getProd().getDroneType());
            }
        }
    }

    public void startProduction(DroneTypes drone) throws BuildingUnfinishedException, NotEnoughResourceException {
        if (!dataEntity.inConstruction() && !dataEntity.prodIsActive()) {
            startProductionAutomatic(drone);
        } else {
            throw new BuildingUnfinishedException();
        }
    }

    /**
     * Started die Produktion einer Drone und verbraucht schonmal die benoetigten Resourcen
     *
     * @param drone: Typ der Drone
     */
    private void startProductionAutomatic(DroneTypes drone) throws NotEnoughResourceException {
        if (canBeBuild(drone)) {
            if (!dataEntity.isProducing() && !dataEntity.inConstruction()) {
                if (dataEntity.getStorage().hasResources(drone.getCosts())) {
                    dataEntity.startProducing();
                    dataEntity.setProducedElement(DroneManagement.typeToDrone(drone));
                    dataEntity.addProductionTime(drone.getConstructionTime());
                    dataEntity.getStorage().useResources(drone.getCosts());
                } else {
                    System.out.println("Du hast nicht genuegend Resourcen fuer diese Drone!");
                }
            } else {
                System.out.println("Es wird bereits eine Drone Produziert!");
            }
        }
    }

    /**
     * Fertigt die Drone an
     */
    private void finishDrone() {
        if (dataEntity.isProducing()) {
            if (dataEntity.isProductionFinished()) {
                dataEntity.setProducing(false);
                if (!dataEntity.prodIsActive()) {
                    DroneManagement.addDrone(dataEntity.getProducedElement());
                } else {
                    dataEntity.addCurrentDroneToProd();
                }
                dataEntity.clearProducedElement();
            }
        }
    }

    private boolean canBeBuild(DroneTypes tmp) {
        return dataEntity.getProducibleDrones().contains(tmp);
    }

    public void activatedProducer() {
        dataEntity.setActivateProd(true);
    }

    public void deactivatedProducer() {
        dataEntity.setActivateProd(false);
    }

    public void loadResources(int amount) throws NotEnoughStorageException, DroneNotEnoughEnergyException, NotEnoughResourceException, MissingTransportDrone, BuildingUnfinishedException {
        if (!(dataEntity.getStorage() instanceof DirectResourceCon)) {
            if (!dataEntity.inConstruction()) {
                ((InternalStorage) dataEntity.getStorage()).loadResources(amount);
            } else {
                throw new BuildingUnfinishedException();
            }
        }
    }

    @Override
    public BuildingInformationElement getInformation() {
        BuildingInformationElement<DronefactoryDataEntity> out =
                new BuildingInformationElement<>(dataEntity);
        return out;
    }

}
