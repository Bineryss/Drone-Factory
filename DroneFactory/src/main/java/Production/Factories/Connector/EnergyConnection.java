package Production.Factories.Connector;

import ImportandEnums.BuildingTypes;
import Management.Resources.Energy;

public class EnergyConnection {
    private Energy energy;

    public EnergyConnection(BuildingTypes type) {
        energy = new Energy(type.getMaxCapacityEnergy(), type.getEnergyUse());
    }

    public boolean hasEnergy() {
        return energy.hasEnergy();
    }

    public void useEnergy() {
        energy.useEnergy();
    }

    public int transferEnergy(int amount) {
        energy.loadEnergy(amount);
        return amount;
    }

    public int availableEnergy() {
        return energy.availableEnergy();
    }

    public String toString() {
        return energy.toString();
    }
}
