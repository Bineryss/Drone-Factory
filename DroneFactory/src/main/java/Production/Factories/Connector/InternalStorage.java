package Production.Factories.Connector;

import ImportandEnums.Type;
import Management.DroneManagement;
import Management.Resources.ResourceManagement;
import Management.Resources.Storage;
import Production.Dronen.Drone;

import javax.inject.Inject;

/**
 * Die Grundanbindung, an das Resourcemanagement.
 *
 * Es benötigt eine Drone zum aufladen und entladen des Lagers
 *
 */
public class InternalStorage implements ResourceConnection {
    @Inject
    ResourceManagement resourceManagement;
    @Inject
    DroneManagement droneManagement;

    private Storage storage;
    private Drone transportDrone;

    public InternalStorage(Type type) {
        this.storage = new Storage(type.getMaxCapacity());
        this.transportDrone = null;
    }

    @Override
    public void storeResources(int amount) {
        if (transportDrone != null && !transportDrone.isDead()) {
            resourceManagement.addResources(storage.empty());
            transportDrone.work();
        } else {
            System.out.println("Keine Drone mehr!");
            transportDrone = null;
        }
    }

    public void loadResources(int amount) {
        if (storage.canStore(amount)) {
            storage.addResources(amount);
        } else {
            System.out.println("So viel kannst du nicht lagern!");
        }
    }


    public boolean isFull() {
        return storage.isFull();
    }

    @Override
    public boolean canStore(int amount) {
        return storage.canStore(amount);
    }

    @Override
    public void removeResources(int amount) {
        storage.removeResources(amount);
    }

    @Override
    public boolean hasResources(int amount) {
        return storage.hasResources(amount);
    }


    /**
     * Zieht eine Drone aus dem Dronemanagement
     *
     * @param drone: Typ der Drone
     */
    public void addTransportDrone(Type drone) {
        transportDrone = droneManagement.getFullDrone(drone);
    }

    public String toString() {
        return storage + printDrone();
    }

    private String printDrone() {
        if (transportDrone != null) {
            return "(" + transportDrone.toString() + " )";
        } else {
            return "";
        }
    }
}
