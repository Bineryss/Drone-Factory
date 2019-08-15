package production.Factories.Connector;

import specificexceptions.DroneNotEnoughEnergyException;
import specificexceptions.NotEnoughResourceException;
import specificexceptions.NotEnoughStorageException;

/**
 * Die Resourcenverwaltung kann mit upgrades errsetzt werde.
 * So ist auf der ersten Stufe jedes Gebäude selbstverantwortlich
 * <p>
 * auf der höchsten Stufe nutzen die Gabaeude einfach die gemeinsamen Resourcen vom Resourcmanager
 */
public interface ResourceConnection {

    void useResources(int amount) throws NotEnoughResourceException;

    boolean hasResources(int amount);

    boolean isFull();

    boolean canStore(int amount);

    void storeResources(int amount) throws NotEnoughResourceException, DroneNotEnoughEnergyException, NotEnoughStorageException;

    int inStorage();
}
