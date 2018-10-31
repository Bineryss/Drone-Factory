package Production.Dronen;

/**
 *
 * Eine Drone, verwaltet produktionskosten, erhaltungskosten, produktivität, lebensspanne und noch mehr.
 * Von Ihr koennen spezialisierte Dronen erben.
 *
 * 0 = DefaultDrone
 */
public abstract class Drone {
    protected String icon = "---";
    protected int id;

    //Kosten zum Dronen Produzieren
    protected int costs[];
    //Produktivitaet der Drone
    protected int producitvity;

    //Energie Kosten beim ruhen
    protected int idelcosts;
    //Energie Speicher
    protected int energy;

    protected int producetime;


    //Gibt das Energie Level der Drone an
    public int getEnergy() {
        return energy;
    }

    public int getProducetime() {
        return producetime;
    }

    //Resourcenkosten um Drone zu produzieren
    public int[] getCosts() {
        return costs;
    }

    //return uebrig gebliebene energy von Gebaeude
    public int useEnergy(int energy) {
        if (!isDead()) {
            if (this.energy - energy <= 0) {
                int tmp = this.energy;
                this.energy = 0;
                return energy - tmp;
            }
            this.energy -= energy;
            return 0;
        }
        return energy;
    }

    //Gibt den Status der Drone an
    public boolean isDead() {
        return energy == 0;
    }

    public int getID() {
        return id;
    }

    public String getIcon() {
        return this.icon;
    }
}
