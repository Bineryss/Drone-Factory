package Production.Factories.Connector;

/**
 * Die Resourcenverwaltung kann mit upgrades errsetzt werde.
 * So ist auf der ersten Stufe jedes Gebäude selbstverantwortlich
 * <p>
 * auf der höchsten Stufe nutzen die Gabaeude einfach die gemeinsamen Resourcen vom Resourcmanager
 */
public interface ResourceConnection {

    void removeResources(int amount);

    boolean hasResources(int amount);

    boolean isFull();

    boolean canStore(int amount);

    void storeResources(int efficiency);
}
