package Production.Dronen;

import Management.Type;
import Management.Resources.Energy;
import Management.Resources.Storage;

/**
 * <h3>Drone</h3>
 * Eine Drone, verwaltet produktionskosten, erhaltungskosten, produktivität, lebensspanne und noch mehr.
 * Von Ihr koennen spezialisierte Dronen erben.
 * <p>
 * 0 = DefaultDrone
 */
public abstract class Drone {
    protected Type type;

    //Kosten zum Dronen Produzieren
    protected int costs;
    //Produktivitaet der Drone
    protected int efficiency;
    protected boolean isOccupied;

    //Energie Speicher
    protected Energy energy;

    //Resource Speicher
    protected Storage resource;

    protected int producetime;


    //Gibt das Energie Level der Drone an
    public boolean hasEnergy() {
        return energy.hasEnergy();
    }

    public int energyLeft() {
        return energy.availableEnergy();
    }

    public boolean hasMaxEnergy() {
        return energy.hasMaxEnergy();
    }

    public int getProducetime() {
        return producetime;
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

    public Type getType() {
        return type;
    }
}
