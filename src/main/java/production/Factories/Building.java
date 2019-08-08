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
import specificexceptions.NotEnoughEnergyException;
import specificexceptions.NotEnoughResourceException;

import java.util.ArrayList;

/**
 * Abstrakte Klasse fuer Gebaeude
 * <p>
 * Speichert die Kosten, Bauzeit,
 * <p>
 */
public abstract class Building {
    protected final BuildingTypes buildingTypes;
    //ID des speziellen Gebaeudes
    protected int id;
    protected final EnergyConnection energy;
    protected ResourceConnection storage;
    protected final int efficiency;

    protected ArrayList<Drone> workers;
    protected int construction;
    private final int constructionCost;
    private boolean hasResources;

    protected Building(BuildingTypes type) {
        this.buildingTypes = type;
        constructionCost = type.getCosts();
        construction = type.getConstructionTime();
        efficiency = type.getEfficiency();
        energy = new EnergyConnection(type);
    }

    public void update() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException {
        if (!isReady()) {
            build();
        } else {
            updateBuilding();
        }
        inform();
    }

    protected abstract void updateBuilding() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException;

    public EnergyConnection getEnergy() throws BuildingUnfinishedException {
        if (energy != null) {
            return energy;
        } else {
            throw new BuildingUnfinishedException();
        }
    }

    public void connectStorage(ResourceConnectionsEnum con) throws BuildingUnfinishedException {
        if (isReady()) {
            ResourceConnection tmp = null;
            switch (con) {
                case INTERNALSTORAGE:
                    tmp = new InternalStorage(buildingTypes);
                    break;
                case DIRECTRESOURCECONNECT:
                    tmp = new DirectResourceCon();
                    break;
            }
            storage = tmp;
        } else {
            throw new BuildingUnfinishedException();
        }
    }

    public ResourceConnection getStorage() throws BuildingUnfinishedException {
        if (storage != null) {
            return storage;

        } else {
            throw new BuildingUnfinishedException();
        }
    }

    /**
     * droneId: zeigt den Dronen typ, der fuer das bauen genutzt werden soll
     * <p>
     * Wenn Drone keine arbeitskraft mehr, dann wird bau gestopt, neu Drone muss uebergen werden.
     */
    public void startConstruction(DroneTypes droneId, int droneCount) throws NotEnoughResourceException {
        if (!inConstruction()) {
            workers = DroneManagement.giveDronesWork(droneId, droneCount);
            if (!hasResources) {
                if (ResourceManagement.hasResources(constructionCost)) {
                    ResourceManagement.removeResources(constructionCost);
                    hasResources = true;
                }
            }
        } else {
            System.out.println(("Ist schon fertig gebaut!"));
        }
    }

    public boolean inConstruction() {
        return construction == 0;
    }

    private void build() throws DroneNotEnoughEnergyException {
        for (Drone worker : workers) {
            if ((construction - worker.efficiency()) > 0) {
                construction -= worker.workEfficiency();
            } else {
                worker.workEfficiency();
                construction = 0;
                worker.hasFinishedWork();
                workers = null;
                break;
            }
        }
    }

    public void addMoreWorkers(DroneTypes droneType, int amount) {
        if (workers != null) {
            workers.addAll(DroneManagement.giveDronesWork(droneType, amount));
        }
    }


    protected boolean isReady() {
        return construction == 0;
    }

    public int getId() {
        return id;
    }

    public BuildingTypes getBuildingTypes() {
        return buildingTypes;
    }

    public abstract BuildingInformationElement inform();
}