package Production.Factories.Connector;

import ImportandEnums.Type;
import Management.Resources.ResourceManagement;

public class DirectEnergyCon implements EnergyConnection {
    private int energyUse;

    public DirectEnergyCon(Type type) {
        energyUse = type.getEnergyUse();
    }

    @Override
    public boolean hasEnergy() {
        return ResourceManagement.hasResources(energyUse);
    }

    @Override
    public void useEnergy() {
        ResourceManagement.useEnergy(energyUse);
    }

    @Override
    public void transferEnergy(int amount) {
        ResourceManagement.addEnergy(amount);
    }

}
