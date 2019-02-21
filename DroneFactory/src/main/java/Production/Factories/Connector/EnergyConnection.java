package Production.Factories.Connector;

public interface EnergyConnection {

    /**
     *
     * @return
     */
    boolean hasEnergy();

    void useEnergy();

    void transferEnergy(int amount);
}
