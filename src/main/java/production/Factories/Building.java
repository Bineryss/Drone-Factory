package production.Factories;

import ImportandEnums.BuildingTypes;
import ImportandEnums.DroneTypes;
import ImportandEnums.ResourceConnectionsEnum;
import management.ManagementSystems.DroneManagement;
import management.ManagementSystems.ResourceManagement;
import production.Dronen.Drone;
import production.Factories.Connector.DirectResourceCon;
import production.Factories.Connector.EnergyConnection;
import production.Factories.Connector.InternalStorage;
import production.Factories.Connector.ResourceConnection;
import specificexceptions.BuildingUnfinishedException;
import specificexceptions.DroneNotEnoughEnergyException;
import specificexceptions.NoResourceConnection;
import specificexceptions.NotEnoughEnergyException;
import specificexceptions.NotEnoughResourceException;
import specificexceptions.NotEnoughStorageException;

/**
 * Abstrakte Klasse fuer Gebaeude
 * <p>
 * Speichert die Kosten, Bauzeit, etc.
 * <p>
 */
public abstract class Building <T extends BuildingDataEntity> implements UIWatchable {
    protected final BuildingTypes buildingType;
    //ID des speziellen Gebaeudes
    protected int id;
    protected T dataEntity;


    protected Building(BuildingTypes type) {
        this.buildingType = type;
        dataEntity = (T) new BuildingDataEntity(type);
    }

    public void update() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException, NotEnoughStorageException {
        if (dataEntity.inConstruction()) {
            build();
        } else {
            updateBuilding();
        }
    }

    protected abstract void updateBuilding() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException, NotEnoughStorageException;

    public EnergyConnection getEnergy() throws BuildingUnfinishedException {
        if (dataEntity.getEnergy() != null) {
            return dataEntity.getEnergy();
        } else {
            throw new BuildingUnfinishedException();
        }
    }

    public void connectStorage(ResourceConnectionsEnum con) throws BuildingUnfinishedException {
        if (!dataEntity.inConstruction()) {
            ResourceConnection tmp = null;
            switch (con) {
                case INTERNALSTORAGE:
                    tmp = new InternalStorage(buildingType);
                    break;
                case DIRECTRESOURCECONNECT:
                    tmp = new DirectResourceCon();
                    break;
            }
            dataEntity.setStorage(tmp);
        } else {
            throw new BuildingUnfinishedException();
        }
    }

    public ResourceConnection getStorage() throws NoResourceConnection {
        if (dataEntity.getStorage() != null) {
            return dataEntity.getStorage();
        } else {
            throw new NoResourceConnection();
        }
    }

    /**
     * droneId: zeigt den Dronen typ, der fuer das bauen genutzt werden soll
     * <p>
     * Wenn Drone keine arbeitskraft mehr, dann wird bau gestopt, neu Drone muss uebergen werden.
     */
    public void startConstruction(DroneTypes droneId, int droneCount) throws NotEnoughResourceException {
        if (dataEntity.inConstruction()) {
            dataEntity.addWorkers(DroneManagement.giveDronesWork(droneId, droneCount));
            if (!dataEntity.hasResourcesForConstruction()) {
                if (ResourceManagement.hasResources(dataEntity.getConstructionCost())) {
                    ResourceManagement.removeResources(dataEntity.getConstructionCost());
                    dataEntity.setResourcesForBuildingAvailable(true);
                }
            }
        } else {
            System.out.println(("Ist schon fertig gebaut!"));
        }
    }

    private void build() throws DroneNotEnoughEnergyException {
        for (Drone worker : dataEntity.getWorkers()) {
            if (dataEntity.wontBeFinshed(worker)) {
                dataEntity.work(worker.workEfficiency());
            } else {
                worker.workEfficiency();
                dataEntity.setConstruction(0);
                worker.hasFinishedWork();
                dataEntity.clearWorkers();
                break;
            }
        }
    }

    public void addMoreWorkers(DroneTypes droneType, int amount) {
        dataEntity.addWorkers(DroneManagement.giveDronesWork(droneType, amount));
    }

    public int getId() {
        return id;
    }

    public BuildingTypes getBuildingType() {
        return buildingType;
    }

}