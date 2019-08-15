package management.ManagementSystems;

import ImportandEnums.DroneTypes;
import production.Dronen.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

/**
 * Speichert alle Dronen, die Produziert wurden
 */
public class DroneManagement {
    private static class DroneKey {
        final DroneTypes type;
        static int number = 0;

        DroneKey(DroneTypes type) {
            this.type = type;
            number++;
        }
    }

    private static final HashMap<DroneKey, Drone> drones = new HashMap<>();


    /**
     * fuegt die Drone anhand ihrer ID in die Richitge Stelle an.
     *
     * @param tmp
     */
    public static void addDrone(Drone... tmp) {
        for (Drone drone : tmp) {
            drones.put(new DroneKey(drone.getType()), drone);
        }
    }

    /**
     * entfernt die 1. Drone, wenn sie keine Energie mehr hat
     */
    private static void removeDead() {
        List<DroneKey> deleteKeys = new ArrayList<>();
        for (DroneKey key : drones.keySet()) {
            deleteKeys.add(key);
        }
        for (DroneKey key : deleteKeys) {
                if (drones.get(key).isDead()) {
                    drones.remove(key);
                }
        }
    }

    /**
     * gibt die 1. Drone, die zu der id gehoert zurueck
     *
     * @param id
     * @return
     */
    public static Drone getDrone(DroneTypes id) {
        for (DroneKey key : drones.keySet()) {
            if (key != null && key.type == id) {
                return drones.get(key);
            }
        }
        throw new InputMismatchException("no such id found!");
    }

    public static Drone getFullDrone(DroneTypes id) {
        ArrayList<Drone> search = cleanDroneList(id);
        for (Drone full : search) {
            if (full.hasMaxEnergy()) {
                return full;
            }
        }
        throw new InputMismatchException("no such id found!");
    }

    public static List<Drone> giveDronesWork(DroneTypes id, int droneCount) {
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


    private static ArrayList<Drone> cleanDroneList(DroneTypes id) {
        removeDead();
        ArrayList<Drone> search = new ArrayList<>();
        for (DroneKey key : drones.keySet()) {
            if (key != null && key.type == id) {
                search.add(drones.get(key));
            }
        }
        return search;
    }

    public static void removeDrone(Drone remove) {
        drones.forEach((key, drone) -> {
            if (key != null && key.type == remove.getType()) {
                if (drone.equals(remove)) {
                    drones.remove(key);
                }
            }
        });
    }

    public static String print() {
        StringBuilder out = new StringBuilder();
        for (DroneKey key : drones.keySet()) {
            if (key != null) {
                out.append(drones.get(key)).append("; ");
            }
        }
        return out.toString();
    }

    private static String getIcon(DroneTypes id) {
        return id.getIcon();
    }

    private static int availableEnergy(DroneTypes id) {
        int availableEnergy = 0;
        ArrayList<Drone> search = cleanDroneList(id);
        for (Drone tmp : search) {
            availableEnergy += tmp.energyLeft();
        }
        return availableEnergy;
    }


    public static Drone typeToDrone(DroneTypes type) {
        return new Drone(type);
    }
}
