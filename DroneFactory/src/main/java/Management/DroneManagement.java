package Management;

import List.List;
import Production.Dronen.*;

/**
 * Speichert alle Dronen, die Produziert wurden
 */
public class DroneManagement {
    //Anzahl aller ID`s
    private static final int IDCOUNT = 1;

    private static List<Drone>[] DRONES = new List[IDCOUNT];
    private static int[] ENERGY = new int[IDCOUNT];




    public static void start() {
        //Bebaeude Typen werden Ihrer ID zugeordnet
        for(int i = 0; i < DRONES.length; i++) {
            DRONES[i] = new List<Drone>();
        }
    }

    /**
     * fuegt die Drone anhand ihrer ID in die Richitge Queue an.
     * @param tmp
     */
    public static void addDrone(Drone tmp) {
        DRONES[tmp.getID()].append(tmp);
    }

    /**
     * entfernt die 1. Drone, wenn sie keine Energie mehr hat
     */
    private static void removeDead() {
        for (int i = 0; i < DRONES.length; i++) {
            List<Drone> dead = DRONES[i];
            dead.toFirst();
            while (dead.hasAccess()) {
                if (dead.getContent().isDead()) {
                    dead.remove();
                    dead.next();
                }else {
                    dead.next();
                }
            }
        }
    }

    /**
     * gibt die 1. Drone, die zu der id gehoert zurueck
     * @param id
     * @return
     */
    public static Drone getDrone(int id) {
        List<Drone> search = cleanDroneList(id);
        return search.getContent();
    }

    public static int availableEnergy(int id) {
        int availableEnergy = 0;
        List<Drone> search = cleanDroneList(id);
        while (search.hasAccess()) {
            availableEnergy += search.getContent().getEnergy();
            search.next();
        }
        return availableEnergy;
    }

    private static String getIcon(int id) {
        List<Drone> search = cleanDroneList(id);
        return search.getContent().getIcon();
    }

    private static List<Drone> cleanDroneList(int id) {
        List<Drone> search = DRONES[id];
        removeDead();
        search.toFirst();
        return search;
    }

    public static void removeDrone(Drone tmp) {
        List<Drone> removal = DRONES[tmp.getID()];
        removal.toFirst();
        while (removal.hasAccess()) {
            if(tmp.equals(removal.getContent())) {
                removal.remove();
                break;
            }
            removal.next();
        }
    }

    public static String print() {
        String str = new String();
        for (int i = 0; i < IDCOUNT; i++) {
            str += (getIcon(i) + ": Energy left: " + availableEnergy(i));
            str += ("\n");
        }
        return str;
    }
}
