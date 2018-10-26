package Production.Dronen;

/**
 *
 * Eine Drone, verwaltet produktionskosten, erhaltungskosten, produktivität, lebensspanne und noch mehr.
 * Von Ihr koennen spezialisierte Dronen erben.
 *
 * 0 = DefaultDrone
 */
public abstract class Drone {
    protected int id;

    //Kosten zum Dronen Produzieren
    protected int costs;
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
    public int getCosts() {
        return costs;
    }

    //return uebrig gebliebene work von Gebaeude
    public int useForce(int work) {
        if (!isDead()) {
            if (energy - work <= 0) {
                int tmp = energy;
                energy = 0;
                return work - tmp;
            }
            energy -= work;
            return 0;
        }
        return work;
    }

    //Gibt den Status der Drone an
    public boolean isDead() {
        return energy == 0;
    }

    public int getID() {
        return id;
    }
}
