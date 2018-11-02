package Production.Factories;

import Management.DroneManagement;
import Management.Resources.Energy;
import Management.Resources.ResourceManagement;
import Production.Dronen.Drone;

import java.util.InputMismatchException;

/**
 * Interface fuer Gebaeude
 * <p>
 * Speichert die Kosten, Bauzeit,
 * <p>
 * ID: 0 = Energy, 1 = Production; 2 = Research; 3 = Resources; 4 = Storage
 */
public abstract class Building {
    //ID des Gebaeude Types
    protected int id;
    //ID des speziellen Gebaeudes
    protected int sid;

    protected int[] constructionCost;
    protected int construction;
    protected boolean hasResources;
    protected Drone[] workers;

    protected Energy energy;

    protected int[] resources;
    protected int[] resourcesStorable;

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
            workers = DroneManagement.getDrones(droneId, droneCount);
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
        for (int i = 0; i < workers.length; i++) {
            if ((construction - workers[i].efficiency()) > 0) {
                construction -= workers[i].work();
            } else {
                workers[i].work();
                construction = 0;
            }
        }
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


    protected boolean hasResources(int[] amount) {
        for (int i = 0; i < resources.length; i++) {
            if ((resources[i] - amount[i]) < 0) {
                return false;
            }
        }
        return true;
    }

    protected void useResources(int[] amount) {
        if (hasResources(amount)) {
            for (int i = 0; i < resources.length; i++) {
                if (resources[i] >= amount[i]) {
                    resources[i] -= amount[i];
                }
            }
        } else {
            throw new InputMismatchException("So viele Resourcen hast du nicht!");
        }
    }

    public void loadResources(int[] amount) {
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

    private boolean canStoreResources(int[] amount) {
        for (int i = 0; i < resources.length; i++) {
            if ((amount[i] + resources[i]) > resourcesStorable[i]) {
                return false;
            }
        }
        return true;
    }

    private void storeResources(int[] amount) {
        resources = ResourceManagement.useResources(amount);
    }


    protected String constructionStatus() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < construction; i++) {
            output.append("|");
        }
        return output.toString();
    }

    protected String printResource() {
        StringBuilder tmp = new StringBuilder();
        tmp.append(energy + " ");
        for (int i = 0; i < resourcesStorable.length; i++) {
            if (resourcesStorable[i] != 0) {
                tmp.append("| " + ResourceManagement.resourceName(i) + ": " + resources[i] + " |");
            }
        }
        tmp.append("|");
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
}