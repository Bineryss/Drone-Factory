package Production.Factories;

import ImportandEnums.*;
import Management.ManagementSystems.DroneManagement;
import Management.ManagementSystems.ResourceManagement;
import ImportandEnums.BuildingTypes;
import Production.Dronen.Drone;
import Production.Factories.Connector.*;
import SpecificExceptions.BuildingUnfinishedException;
import SpecificExceptions.DroneNotEnoughEnergyException;
import SpecificExceptions.NotEnoughEnergyException;
import SpecificExceptions.NotEnoughResourceException;

import java.util.ArrayList;

/**
 * Abstrakte Kalsse fuer Gebaeude
 * <p>
 * Speichert die Kosten, Bauzeit,
 * <p>
 */
public abstract class Building {
    protected BuildingTypes buildingTypes;
    //ID des speziellen Gebaeudes
    protected int id;
    protected EnergyConnection energy;
    protected ResourceConnection storage;
    protected int efficiency;

    private int constructionCost;
    private int construction;
    private boolean hasResources;
    private ArrayList<Drone> workers;

    public Building(BuildingTypes type) {
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

    protected String constructionStatus() {
        StringBuilder out = new StringBuilder();
        for (int i = construction; i > 0; i--) {
            if (i >= 5) {
                out.append("X");
                i -= 4;
            } else {
                out.append("|");
            }
        }
        return out.toString();
    }

    protected String printResource() {
        return String.format("%s%S", energy, storage);
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

    public String getIcon() {
        return buildingTypes.getIcon();
    }
}