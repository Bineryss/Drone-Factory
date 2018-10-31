package Production.Factories;

import Management.DroneManagement;
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

    protected int energy;
    protected int energyUse;
    protected int energyStorable;

    //TODO: besseres als Integer Array finden! Klasse oder Interface!
    protected int[] resources;
    protected int[] resourcesStorable;

    protected int efficency;

    public abstract void update();

    /**
     * tmp: Drone wird uebergeben und Arbeitet mit ihrer Produktivitaet
     * Meherer Dronen koennen gleichzeitig arbeiten
     * <p>
     * Wenn Drone keine arbeitskraft mehr, dann wird bau gestopt, neu Drone muss uebergen werden.
     */
    public void build(int droneId) {
        Drone tmp = DroneManagement.getDrone(droneId);
        if (ResourceManagement.hasResources(constructionCost)) {
            construction = tmp.useEnergy(construction);
            if (tmp.useEnergy(construction) == 0) {
                ResourceManagement.useResources(constructionCost);
            }
        }
    }


    protected boolean hasEnergy() {
        return (energy - energyUse) >= 0;
    }

    protected void useEnergy() {
        if ((energy - energyUse) >= 0) {
            energy -= energyUse;
        } else {
            throw new InputMismatchException("So viel Energie hast du nicht!");
        }
    }

    public void loadEnergy(int energy) {
        if ((this.energy + energy) <= energyStorable && isReady()) {
            try {
                this.energy += ResourceManagement.useResources(0, energy);
            } catch (IllegalArgumentException e) {
                System.out.println("So viel Energie hast du nicht!");
            }
        } else {
            throw new IllegalArgumentException("So viel kannst du nicht lagern!");
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
                resources[i] += ammount[i];
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
        for (int i = 0; i < resources.length; i++) {
            resources[i] += ammount[i];
        }
    }


    protected String constructionStatus() {
        if (construction > 0) {
            return ": X";
        }
        return "";
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