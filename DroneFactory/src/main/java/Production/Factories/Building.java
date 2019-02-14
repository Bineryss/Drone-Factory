package Production.Factories;

import Management.DroneManagement;
import Management.Resources.Energy;
import Management.Resources.Resource;
import Management.Resources.ResourceManagement;
import Management.Resources.Storage;
import Production.Dronen.Drone;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Interface fuer Gebaeude
 * <p>
 * Speichert die Kosten, Bauzeit,
 * <p>
 * ID: 0 = Energy, 1 = Production; 2 = Research; 3 = Resources; 4 = Storage
 */
public abstract class Building {
    protected String ICON;
    //ID des Gebaeude Types
    protected int id;
    //ID des speziellen Gebaeudes
    protected int sid;

    protected int constructionCost;
    protected int construction;
    protected boolean hasResources;
    protected ArrayList<Drone> workers;

    protected Energy energy;

    protected Resource resources;

    protected int efficency;

    public void update() {
        if (construction != 0) {
            build();
        } else {
            updateBuilding();
        }
    }

    public abstract void updateBuilding();

    /**
     * droneId: zeigt den Dronen typ, der fuer das bauen genutzt werden soll
     * <p>
     * Wenn Drone keine arbeitskraft mehr, dann wird bau gestopt, neu Drone muss uebergen werden.
     */
    public void startConstruction(int droneId, int droneCount) {
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

    private void build() {
        for (Drone worker: workers) {
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

    public void addMoreWorkers(int droneId, int amount) {
        workers.addAll(DroneManagement.giveDronesWork(droneId,amount));
    }

    public boolean isWorking() {
        return construction == 0;
    }


    protected boolean hasEnergy() {
        return energy.hasEnergy();
    }

    protected void useEnergy() {
        energy.useResources(efficency);
    }

    public void loadEnergy(int amount) {
        if (isReady()) {
            try {
                ResourceManagement.addEnergy(energy.loadEnergy(ResourceManagement.useEnergy(amount)));
            } catch (IllegalArgumentException e) {
                System.out.println("So viel Energie kannst du nicht Lagern!");
            }
        } else {
            throw new IllegalArgumentException("Gebaude nicht fertig!");
        }
    }


    protected boolean hasResources(int amount) {
        return resources.hasResources(amount);
    }

    protected void useResources(int amount) {
        resources.useResources(amount);
    }

    public void loadResources(int amount) {
        if (canStoreResources(amount) && isReady()) {
            try {
                storeResources(amount);
            } catch (IllegalArgumentException e) {
                System.out.println("So viele Resourcen hast du nicht!");
            }
        } else {
            throw new IllegalArgumentException("So viel kannst du nicht lagern!");
        }
    }

    private boolean canStoreResources(int amount) {
        return resources.addResources(amount);
    }

    private void storeResources(int amount) {
        resources.addResources(ResourceManagement.useResources(amount));
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
        StringBuilder tmp = new StringBuilder();
        tmp.append(energy + " ");
        tmp.append(resources);
        return tmp.toString();
    }

    protected boolean isReady() {
        return construction == 0;
    }

    public int getSID() {
        return sid;
    }

    public int getID() {
        return id;
    }

    public String getIcon() {
        return ICON;
    }
}