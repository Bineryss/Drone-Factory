package Management;

import Production.Dronen.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Speichert alle Dronen, die Produziert wurden
 */
public class DroneManagement {
    //Anzahl aller ID`s
    private static final int IDCOUNT = 1;

    private static ArrayList<Drone>[] DRONES = new ArrayList[IDCOUNT];
    private static int[] ENERGY = new int[IDCOUNT];


    public static void start() {
        //Dronen Typen werden Ihrer ID zugeordnet
        for (int i = 0; i < DRONES.length; i++) {
            DRONES[i] = new ArrayList<Drone>();
        }
    }

    /**
     * fuegt die Drone anhand ihrer ID in die Richitge Queue an.
     *
     * @param tmp
     */
    public static void addDrone(Drone tmp) {
        Type type = tmp.getType();
        switch (type) {
            case DEFAULTDRONE:
                DRONES[0].add(tmp);
                break;
        }
    }

    /**
     * entfernt die 1. Drone, wenn sie keine Energie mehr hat
     */
    private static void removeDead() {
        for (int i = 0; i < DRONES.length; i++) {
            Iterator<Drone> dead = DRONES[i].iterator();
            while (dead.hasNext()) {
                Drone deadDrone = dead.next();
                if (deadDrone.isDead()) {
                    dead.remove();
                }
            }
        }
    }

    /**
     * gibt die 1. Drone, die zu der id gehoert zurueck
     *
     * @param id
     * @return
     */
    public static Drone getDrone(int id) {
        ArrayList<Drone> search = cleanDroneList(id);
        return search.get(0);
    }

    public static Drone getFullDrone(int id) {
        ArrayList<Drone> search = cleanDroneList(id);
        for (Drone full : search) {
            if (full.hasMaxEnergy()) {
                return full;
            }
        }
        return null;
    }

    public static ArrayList<Drone> giveDronesWork(int id, int droneCount) {
        ArrayList<Drone> search = cleanDroneList(id);
        ArrayList<Drone> out = new ArrayList<>();
        if (droneCount <= search.size()) {
            for (int i = 0; i < droneCount; i++) {
                for (Drone tmp : search) {
                    if (!tmp.hasWorkToDo()) {
                        out.add(tmp);
                        tmp.occupied();
                        break;
                    }
                }
            }
        }
        return out;
    }

    public static int availableEnergy(int id) {
        int availableEnergy = 0;
        ArrayList<Drone> search = cleanDroneList(id);
        for (Drone tmp : search) {
            availableEnergy += tmp.energyLeft();
        }
        return availableEnergy;
    }

    private static String getIcon(int id) {
        ArrayList<Drone> search = cleanDroneList(id);
        return search.get(0).getType().getIcon();
    }

    private static ArrayList<Drone> cleanDroneList(int id) {
        ArrayList<Drone> search = DRONES[id];
        removeDead();
        return search;
    }

    public static void removeDrone(Drone remove) {
        Type type = remove.getType();
        switch (type) {
            case DEFAULTDRONE:
                ArrayList<Drone> removal = DRONES[0];
                removal.remove(remove);
                break;
        }
    }

    public static String print() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < IDCOUNT; i++) {
            str.append(getIcon(i) + ": total Energy: " + availableEnergy(i));
            str.append("\n");
        }
        return str.toString();
    }
}
