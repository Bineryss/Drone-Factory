package Production.Factories.Connector;

import ImportandEnums.BuildingTypes;
import Management.ManagementSystems.ResourceManagement;
import Management.Resources.Energy;
import SpecificExceptions.NotEnoughEnergyException;
import SpecificExceptions.NotEnoughResourceException;

public class EnergyConnection {
    private final Energy energy;

    public EnergyConnection(BuildingTypes type) {
        energy = new Energy(type.getMaxCapacityEnergy(), type.getEnergyUse());
    }

    public boolean hasEnergy() {
        return energy.hasEnergy();
    }

    public void useEnergy() throws NotEnoughEnergyException {
        energy.useEnergy();
    }

    public int loadEnergy(int amount) throws NotEnoughEnergyException {
        energy.loadEnergy(amount);
        ResourceManagement.useEnergy(amount);
        return amount;
    }

    public void transferEnergy(int amount) throws NotEnoughEnergyException {
        try {
            energy.removeResources(amount);
        }catch (NotEnoughResourceException e) {
            throw new NotEnoughEnergyException();
        }
        ResourceManagement.addEnergy(amount);
    }

    public int availableEnergy() {
        return energy.availableEnergy();
    }

    public String toString() {
        return energy.toString();
    }
}
