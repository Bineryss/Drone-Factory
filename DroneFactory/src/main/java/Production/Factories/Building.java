package Production.Factories;

import BuildingExtensions.Extension;
import Management.DroneManagement;
import Management.Resources.Energy;
import Management.Resources.ResourceManagement;
import Management.Resources.Storage;
import ImportandEnums.Type;
import Production.Dronen.Drone;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface fuer Gebaeude
 * <p>
 * Speichert die Kosten, Bauzeit,
 * <p>
 * ID: 0 = Energy, 1 = Production; 2 = Research; 3 = Resources; 4 = Vault
 */
public abstract class Building {
    protected Type type;
    //ID des speziellen Gebaeudes
    protected int id;

    protected int constructionCost;
    protected int construction;
    protected boolean hasResources;
    protected ArrayList<Drone> workers;

    protected Energy energy;

    protected Storage storage;

    protected int efficiency;

    public void update() {
        if (!isReady()) {
            build();
        } else {
            updateBuilding();
        }
    }

    protected abstract void updateBuilding();

    /**
     * droneId: zeigt den Dronen typ, der fuer das bauen genutzt werden soll
     * <p>
     * Wenn Drone keine arbeitskraft mehr, dann wird bau gestopt, neu Drone muss uebergen werden.
     */
    public void startConstruction(Type droneId, int droneCount) {
        if (construction > 0) {
            workers = DroneManagement.giveDronesWork(droneId, droneCount);
            if (!hasResources) {
                if (ResourceManagement.hasResources(constructionCost)) {
                    ResourceManagement.useResources(constructionCost);
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

    private void build() {
        for (Drone worker : workers) {
            if ((construction - worker.efficiency()) > 0) {
                construction -= worker.work();
            } else {
                worker.work();
                construction = 0;
                worker.hasFinishedWork();
                workers = null;
                break;
            }
        }
    }

    public void addMoreWorkers(Type droneType, int amount) {
        if (workers != null) {
            workers.addAll(DroneManagement.giveDronesWork(droneType, amount));
        }
    }


    public void loadEnergy(int amount) {
        if (isReady()) {
            if (energy.canStore(amount)) {
                energy.loadEnergy(amount);
            }else {
                System.out.println("So viel Energie kann nicht gelagert werden!");
            }
        } else {
            System.out.println("Gebaude nicht fertig!");
        }
    }

    public void loadResources(int amount) {
        if (storage.canStore(amount) && isReady()) {
            storage.addResources(amount);
        } else {
            throw new IllegalArgumentException("So viel kannst du nicht lagern!");
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
        return String.format("%s %S", energy, storage);
    }

    protected boolean isReady() {
        return construction == 0;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getIcon() {
        return type.getIcon();
    }
}