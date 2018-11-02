package Production.Dronen;

import Management.Resources.Energy;

/**
 * Eine Drone, verwaltet produktionskosten, erhaltungskosten, produktivität, lebensspanne und noch mehr.
 * Von Ihr koennen spezialisierte Dronen erben.
 * <p>
 * 0 = DefaultDrone
 */
public abstract class Drone {
    protected String icon = "---";
    protected int id;

    //Kosten zum Dronen Produzieren
    protected int costs[];
    //Produktivitaet der Drone
    protected int efficiency;

    //Energie Speicher
    protected Energy energy;

    protected int producetime;


    //Gibt das Energie Level der Drone an
    public boolean hasEnergy() {
        return energy.hasEnergy();
    }

    public int energyLeft() {
        return energy.availableEnergy();
    }

    public int getProducetime() {
        return producetime;
    }

    //Resourcenkosten um Drone zu produzieren
    public int[] getCosts() {
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

    public int efficiency() {
        return efficiency;
    }

    //Gibt den Status der Drone an
    public boolean isDead() {
        return energy.availableEnergy() == 0;
    }

    public int getID() {
        return id;
    }

    public String getIcon() {
        return this.icon;
    }
}
