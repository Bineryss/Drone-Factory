package Production.Dronen;

import ImportandEnums.DroneTypes;
import ImportandEnums.Type;
import Management.Resources.Energy;
import Management.Resources.Storage;

/**
 * <h3>Drone</h3>
 * Eine Drone, verwaltet produktionskosten, erhaltungskosten, produktivität, lebensspanne und noch mehr.
 * Von Ihr koennen spezialisierte Dronen erben.
 * <p>
 */
public class Drone {
    private final DroneTypes type;
    private final String icon;

    //Kosten zum Dronen Produzieren
    private int costs;
    //Produktivitaet der Drone
    private int efficiency;
    private boolean isOccupied;

    private Energy energy;
    private Storage resource;

    private int constructionTime;

    public Drone(DroneTypes type, String icon, int costs, int efficiency, int energyUse, int energyCapacity, int resourceCapacity, int constructionTime) {
        this.type = type;
        this.icon = icon;
        this.costs = costs;
        this.efficiency = efficiency;
        isOccupied = false;
        energy = new Energy(energyCapacity, energyUse);
        resource = new Storage(resourceCapacity);
        this.constructionTime = constructionTime;
    }


    //Gibt das Energie Level der Drone an
    private boolean hasEnergy() {
        return energy.hasEnergy();
    }

    public int energyLeft() {
        return energy.availableEnergy();
    }

    public boolean hasMaxEnergy() {
        return energy.hasMaxEnergy();
    }

    public int getConstructionTime() {
        return constructionTime;
    }

    //Resourcenkosten um Drone zu produzieren
    public int getCosts() {
        return costs;
    }

    //return die efficiency, mit der am gebauede gearbeitet wird
    public int work() {
        if (!isDead()) {
            if (hasEnergy()) {
                energy.useEnergy();
            }
            return efficiency;
        }
        return 0;
    }

    public void occupied() {
        isOccupied = true;
    }

    public boolean hasWorkToDo() {
        return isOccupied;
    }

    public void hasFinishedWork() {
        isOccupied = true;
    }

    public int efficiency() {
        return efficiency;
    }

    //Gibt den Status der Drone an
    public boolean isDead() {
        return energy.availableEnergy() == 0;
    }

    public DroneTypes getType() {
        return type;
    }

    public String toString() {
        return icon + " : " + energyLeft();
    }

}
