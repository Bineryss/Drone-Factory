package Management;

import ImportandEnums.DroneTypes;
import Production.Dronen.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Speichert alle Dronen, die Produziert wurden
 */
public class DroneManagement {

    private HashMap<DroneTypes, Drone> drones;


    /**
     * fuegt die Drone anhand ihrer ID in die Richitge Stelle an.
     *
     * @param tmp
     */
    public void addDrone(Drone tmp) {
        drones.put(tmp.getType(), tmp);
    }

    /**
     * entfernt die 1. Drone, wenn sie keine Energie mehr hat
     */
    private void removeDead() {
        for (int i = 0; i < drones.length; i++) {
            Iterator<Drone> dead = drones[i].iterator();
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

    public ArrayList<Drone> giveDronesWork(DroneTypes id, int droneCount) {
        ArrayList<Drone> search = cleanDroneList(typeToId(id));
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

    public int availableEnergy(int id) {
        int availableEnergy = 0;
        ArrayList<Drone> search = cleanDroneList(id);
        for (Drone tmp : search) {
            availableEnergy += tmp.energyLeft();
        }
        return availableEnergy;
    }

    private String getIcon(int id) {
        return getBlueprint()
    }

    private ArrayList<Drone> cleanDroneList(int id) {
        ArrayList<Drone> search = drones[id];
        removeDead();
        return search;
    }

    public void removeDrone(Drone remove) {
        ArrayList<Drone> removal = drones[typeToId(remove.getType())];
        removal.remove(remove);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < drones.length; i++) {
            str.append(getIcon(i) + ": total Energy: " + availableEnergy(i));
            str.append("\n");
        }
        return str.toString();
    }

    public static Drone getBlueprint(DroneTypes type) {
        ApplicationContext context = new ClassPathXmlApplicationContext("DroneConfig.xml");
        return (Drone) context.getBean(type.getXmlName());
    }
}
