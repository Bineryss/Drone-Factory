package production.Factories.Produktion.Dronefactory;

import BuildingExtensions.DroneProducerExt;
import ImportandEnums.DroneTypes;
import management.ManagementSystems.DroneManagement;
import ImportandEnums.BuildingTypes;
import production.Dronen.Drone;
import production.Factories.Building;
import production.Factories.Connector.DirectResourceCon;
import production.Factories.Connector.InternalStorage;
import specificexceptions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Dronefactory - Produziert Dronen aller art.
 * <p>
 * ID: 1
 */
public class DroneFactory extends Building {
    private static int cc = -1;

    private int workStatus;
    private boolean isWorking;

    private static List<DroneTypes> producibleDronesId;
    private Drone producedElement;

    private DroneProducerExt prod;
    private boolean activateProd;

    private DroneFactoryInformationElement informationElement;


    public DroneFactory() {
        super(BuildingTypes.DRONEFACTORY);
        cc++;
        id = cc;
        producibleDronesId = new ArrayList<>();
        producibleDronesId.add(DroneTypes.BUILDINGDRONE);
        producibleDronesId.add(DroneTypes.CARRIERDRONE);
        producibleDronesId.add(DroneTypes.DEFAULTDRONE);
        informationElement = new DroneFactoryInformationElement();
    }

    /**
     * Jeweils ein Zyclus beudeutet eine Runde
     * Die Fabrik benoetigt eine gewisse anzahl an Runden um die entschprechende Drone zu Produzieren
     * Die Fabrik verbraucht pro Runde jeweils einen gewissen betrag an Energie
     * Ist keine Energie mehr vorhanden, wird nicht weitergearbeitet
     */
    @Override
    protected void updateBuilding() throws NotEnoughResourceException, NotEnoughEnergyException {
        if (isReady()) {
            if (isWorking && energy.hasEnergy()) {
                energy.useEnergy();
                workStatus -= efficiency;
                finishDrone();
            }
            if (activateProd) {
                droneExtension();
            }
        }
        inform();
    }

    public void addDroneProducerExtension(DroneTypes drone) {
        if (isReady()) {
            prod = new DroneProducerExt(drone);
        }
    }

    private void droneExtension() throws NotEnoughResourceException {
        if (prod != null) {
            if (!isWorking && !prod.isFull()) {
                startProductionAutomatic(prod.getDroneType());
            }
        }
    }

    public void startProduction(DroneTypes drone) throws BuildingUnfinishedException, NotEnoughResourceException {
        if (isReady() && !activateProd) {
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
            if (!isWorking && isReady()) {
                if (storage.hasResources(drone.getCosts())) {
                    isWorking = true;
                    producedElement = DroneManagement.typeToDrone(drone);
                    workStatus += drone.getConstructionTime();
                    storage.useResources(drone.getCosts());
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
        if (isWorking) {
            if (workStatus == 0) {
                isWorking = false;
                if (!activateProd) {
                    DroneManagement.addDrone(producedElement);
                } else {
                    prod.addDrone(producedElement);
                }
                producedElement = null;
            }
        }
    }

    private boolean canBeBuild(DroneTypes tmp) {
        return producibleDronesId.contains(tmp);
    }

    public void activatedProducer() {
        activateProd = true;
    }

    public void deactivatedProducer() {
        activateProd = false;
    }

    public void loadResources(int amount) throws NotEnoughStorageException, DroneNotEnoughEnergyException, NotEnoughResourceException, MissingTransportDrone, BuildingUnfinishedException {
        if (!(storage instanceof DirectResourceCon)) {
            if (isReady()) {
                ((InternalStorage) storage).loadResources(amount);
            } else {
                throw new BuildingUnfinishedException();
            }
        }
    }

    public DroneFactoryInformationElement inform() {
        return informationElement.update(energy, storage, construction, efficiency, workers, workStatus,
                isWorking, producedElement, prod, producibleDronesId);
    }
}
