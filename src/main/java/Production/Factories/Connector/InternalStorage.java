package Production.Factories.Connector;

import ImportandEnums.DroneTypes;
import ImportandEnums.BuildingTypes;
import Management.ManagementSystems.*;
import Management.Resources.Storage;
import Production.Dronen.Drone;
import SpecificExceptions.DroneNotEnoughEnergyException;
import SpecificExceptions.MissingTransportDrone;
import SpecificExceptions.NotEnoughResourceException;
import SpecificExceptions.NotEnoughStorageException;

/**
 * Die Grundanbindung, an das Resourcemanagement.
 *
 * Es benötigt eine Drone zum aufladen und entladen des Lagers
 *
 */
public class InternalStorage implements ResourceConnection {
    private final Storage storage;
    private Drone transportDrone;

    public InternalStorage(BuildingTypes type) {
        this.storage = new Storage(type.getMaxCapacity());
        this.transportDrone = null;
    }

    /**
     * Ausladen des Internen Lagers
     * Resourcen werden ins Hauptlager geschoben
     * Es wird Energy von der Drone verbraucht
     * @param amount
     */
    public void storeResources(int amount) throws NotEnoughResourceException, DroneNotEnoughEnergyException {
        if (transportDrone != null && !transportDrone.isDead()) {
            ResourceManagement.addResources(storage.removeResources(amount));
            transportDrone.workEfficiency();
        } else {
            System.out.println("Keine Drone mehr!");
            transportDrone = null;
        }
    }

    /**
     * Laed das Interne Lager auf
     * Resourcen werden aus dem Hauptlager genommen
     * Es wird Energy von der Drone verbraucht
     * @param amount
     */
    public void loadResources(int amount) throws NotEnoughStorageException, DroneNotEnoughEnergyException, NotEnoughResourceException, MissingTransportDrone {
        if (storage.canStore(amount)) {
            if(transportDrone != null && !transportDrone.isDead()) {
            ResourceManagement.removeResources(amount);
                storage.addResources(amount);
                transportDrone.workEfficiency();
            }else {
                throw new MissingTransportDrone();
            }
        } else {
            throw new NotEnoughStorageException();
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
    public void useResources(int amount) throws NotEnoughResourceException {
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
    public void addTransportDrone(DroneTypes drone) {
        transportDrone = DroneManagement.getFullDrone(drone);
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
