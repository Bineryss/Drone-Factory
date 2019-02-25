package Management;

import ImportandEnums.DroneTypes;
import Production.Dronen.*;
import Production.Dronen.Normal.DefaultDrone;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Speichert alle Dronen, die Produziert wurden
 */
public class DroneManagement {
    private static class DroneKey {
        DroneTypes type;
        int number;

        DroneKey(DroneTypes type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    private static int droneCount;
    private static HashMap<DroneKey, Drone> drones = new HashMap<>();


    /**
     * fuegt die Drone anhand ihrer ID in die Richitge Stelle an.
     *
     * @param tmp
     */
    public static void addDrone(Drone tmp) {
        drones.put(new DroneKey(tmp.getType(), droneCount), tmp);
        droneCount++;
    }

    /**
     * entfernt die 1. Drone, wenn sie keine Energie mehr hat
     */
    private static void removeDead() {
        for (DroneKey key: drones.keySet()) {
            if(key != null) {
                if(drones.get(key).isDead()) {
                    drones.remove(key);
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
    public static Drone getDrone(DroneTypes id) {
        for (DroneKey key: drones.keySet()) {
            if(key != null && key.type == id) {
                return drones.get(key);
            }
        }
        return null;
    }

    public Drone getFullDrone(DroneTypes id) {
        ArrayList<Drone> search = cleanDroneList(id);
        for (Drone full : search) {
            if (full.hasMaxEnergy()) {
                return full;
            }
        }
        return null;
    }

    public static ArrayList<Drone> giveDronesWork(DroneTypes id, int droneCount) {
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

    public static int availableEnergy(DroneTypes id) {
        int availableEnergy = 0;
        ArrayList<Drone> search = cleanDroneList(id);
        for (Drone tmp : search) {
            availableEnergy += tmp.energyLeft();
        }
        return availableEnergy;
    }

    private static String getIcon(DroneTypes id) {
        return id.getIcon();
    }

    private static ArrayList<Drone> cleanDroneList(DroneTypes id) {
        ArrayList<Drone> search = new ArrayList<>();
        for (DroneKey key: drones.keySet()) {
            if(key != null && key.type == id) {
                search.add(drones.get(key));
            }
        }
        return search;
    }

    public static void removeDrone(Drone remove) {
        for (DroneKey key: drones.keySet()) {
            if(key != null && key.type == remove.getType()) {
                if(drones.get(key).equals(remove)) {
                    drones.remove(key);
                }
            }
        }
    }

    public static String print() {
        String out = "";
        for (DroneKey key: drones.keySet()) {
            if(key != null) {
                out += drones.get(key);
            }
        }
        return out;
    }


    public static final Drone typeToDrone(DroneTypes type) {
        switch (type) {
            case DEFAULTDRONE:
                return new DefaultDrone();
            case CARRIERDRONE:
                //return new CarrierDrone();
            default:
                return null;
        }
    }
}
