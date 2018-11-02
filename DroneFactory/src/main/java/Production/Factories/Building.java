package Production.Factories;

import Management.DroneManagement;
import Management.Resources.Energy;
import Management.Resources.Resource;
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
    //ID des speziellen Gebaeude
    protected int sid;

    protected int[] constructionCost;
    protected int construction;
    protected boolean hasResources;

    protected Energy energy;

    protected int[] resources;
    protected int[] resourcesStorable;

    protected int efficency;

    public abstract void update();

    /**
     * tmp: Drone wird uebergeben und Arbeitet mit ihrer Produktivitaet
     * <p>
     * Wenn Drone keine arbeitskraft mehr, dann wird bau gestopt, neu Drone muss uebergen werden.
     */
    public void build(int droneId) {
        if (construction > 0) {
            Drone tmp = DroneManagement.getDrone(droneId);
            if (!hasResources) {
                if (ResourceManagement.hasResources(constructionCost)) {
                    ResourceManagement.useResources(constructionCost);
                    hasResources = true;
                }
            }
            construction = tmp.useEnergy(construction);
        } else {
            System.out.println(("Ist schon fertig gebaut!"));
        }
    }


    protected boolean hasEnergy() {
        return energy.hasEnergy();
    }

    protected void useEnergy() {
        energy.useResources(efficency);
    }

    public void loadEnergy(int ammount) {
        if (isReady()) {
            try {
                ResourceManagement.addEnergy(energy.loadEnergy(ResourceManagement.useEnergy(ammount)));
            } catch (IllegalArgumentException e) {
                System.out.println("So viel Energie kannst du nicht Lagern!");
            }
        } else {
            throw new IllegalArgumentException("Gebaude nicht fertig!");
        }
    }


    protected boolean hasResources(int[] ammount) {
        for (int i = 0; i < resources.length; i++) {
            if ((resources[i] - ammount[i]) < 0) {
                return false;
            }
        }
        return true;
    }

    protected void useResources(int[] ammount) {
        if (hasResources(ammount)) {
            for (int i = 0; i < resources.length; i++) {
                if(resources[i] >= ammount[i]) {
                resources[i] -= ammount[i];
                }
            }
        } else {
            throw new InputMismatchException("So viele Resourcen hast du nicht!");
        }
    }

    public void loadResources(int[] ammount) {
        if (canStoreResources(ammount) && isReady()) {
            try {
                storeResources(ammount);
            } catch (IllegalArgumentException e) {
                System.out.println("So viele Resourcen hast du nicht!");
            }
        } else {
            throw new IllegalArgumentException("So viel kannst du nicht lagern!");
        }
    }

    private boolean canStoreResources(int[] ammount) {
        for (int i = 0; i < resources.length; i++) {
            if ((ammount[i] + resources[i]) > resourcesStorable[i]) {
                return false;
            }
        }
        return true;
    }

    private void storeResources(int[] ammount) {
        resources = ResourceManagement.useResources(ammount);
    }


    protected String constructionStatus() {
        if (construction > 0) {
            return ": X";
        }
        return "";
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