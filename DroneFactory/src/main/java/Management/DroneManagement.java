package Management;

import ImportandEnums.DroneTypes;
import Production.Dronen.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Speichert alle Dronen, die Produziert wurden
 */
public class DroneManagement {
    private class DroneKey {
        DroneTypes type;
        int number;

        DroneKey(DroneTypes type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    private int droneCount;
    private HashMap<DroneKey, Drone> drones;

    public DroneManagement() {
        drones = new HashMap<>();
        droneCount = 0;
    }


    /**
     * fuegt die Drone anhand ihrer ID in die Richitge Stelle an.
     *
     * @param tmp
     */
    public void addDrone(Drone tmp) {
        drones.put(new DroneKey(tmp.getType(), droneCount), tmp);
        droneCount++;
    }

    /**
     * entfernt die 1. Drone, wenn sie keine Energie mehr hat
     */
    private void removeDead() {
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

    public ArrayList<Drone> giveDronesWork(DroneTypes id, int droneCount) {
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

    public int availableEnergy(DroneTypes id) {
        int availableEnergy = 0;
        ArrayList<Drone> search = cleanDroneList(id);
        for (Drone tmp : search) {
            availableEnergy += tmp.energyLeft();
        }
        return availableEnergy;
    }

    private String getIcon(DroneTypes id) {
        ApplicationContext context = new ClassPathXmlApplicationContext("DroneConfig.xml");
        Drone tmp = (Drone) context.getBean(id.getXmlName());
        return tmp.getIcon();
    }

    private ArrayList<Drone> cleanDroneList(DroneTypes id) {
        ArrayList<Drone> search = new ArrayList<>();
        for (DroneKey key: drones.keySet()) {
            if(key != null && key.type == id) {
                search.add(drones.get(key));
            }
        }
        return search;
    }

    public void removeDrone(Drone remove) {
        for (DroneKey key: drones.keySet()) {
            if(key != null && key.type == remove.getType()) {
                if(drones.get(key).equals(remove)) {
                    drones.remove(key);
                }
            }
        }
    }

    public String toString() {
        String out = "";
        for (DroneKey key: drones.keySet()) {
            if(key != null) {
                out += drones.get(key);
            }
        }
        return out;
    }

    public static Drone getBlueprint(DroneTypes type) {
        ApplicationContext context = new ClassPathXmlApplicationContext("DroneConfigs.xml");
        return (Drone) context.getBean(type.getXmlName());
    }
}
