package Production.Factories.Resources;

import Management.DroneManagement;
import Management.Resources.Energy;
import ImportandEnums.Type;
import Management.Resources.ResourceManagement;
import Management.Resources.Storage;
import Production.Dronen.Drone;
import Production.Factories.Building;


/**
 * <h3>Extractor</h3>
 * Produziert Resourcen, wenn genug Energie vorhanden ist.
 * <p>
 * ID: 3
 */
public class Extractor extends Building {
    private static int cc = -1;

    private Drone transportDrone;

    public Extractor() {
        super();
        cc++;
        id = cc;

        type = Type.EXTRACTOR;

        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        constructionCost = Type.EXTRACTOR.getCosts();
        construction = Type.EXTRACTOR.getConstructionTime();

        energy = new Energy(100, 5);
        efficiency = 2;

        storage = new Storage(Type.EXTRACTOR.getMaxCapacity());
        transportDrone = null;
    }

    public void updateBuilding() {
        if (isReady()) {
            if (transportDrone != null && storage.isFull()) {
                storeResources();
            }
            produceResources();
        }
    }

    public void storeResources() {
        if (transportDrone != null && !transportDrone.isDead()) {
            ResourceManagement.addResources(storage.empty());
            removeResources();
            transportDrone.work();
        } else {
            System.out.println("Keine Drone mehr!");
            transportDrone = null;
        }
    }

    private void removeResources() {
        storage.useResources(storage.getResources());
    }

    /**
     * Zieht eine Drone aus dem Dronemanagement
     *
     * @param id: Typ der Drone
     */
    public void addDrone(int id) {
        if (transportDrone == null) {
            transportDrone = DroneManagement.getFullDrone(id);
            DroneManagement.removeDrone(transportDrone);
        } else {
            DroneManagement.addDrone(transportDrone);
            transportDrone = DroneManagement.getDrone(id);
        }
    }

    private void produceResources() {
        if (!storage.isFull()) {
            try {
                energy.useEnergy();
                extractResource();
            } catch (IllegalArgumentException e) {
                System.out.println("So viel Energie ist nicht vorhanden!");
            }
        }
    }

    private void extractResource() {
        if (!storage.canStore(efficiency)) {
            int filled = storage.getResources();
            int availableSpace = storage.getMaxCapacity() - filled;
            storage.addResources(availableSpace);
        }

    }


    /**
     * @return: Fertige Ausgabe
     */
    public String toString() {
        return "[ " + type.getIcon() + " |" + printResource() + " (" + printDrone() + ") ]" + constructionStatus();
    }

    private String printDrone() {
        if (transportDrone != null) {
            return transportDrone.toString();
        } else {
            return "";
        }
    }

}
