package Production.Factories.Connector;

import ImportandEnums.Type;
import Management.Resources.ResourceManagement;

import javax.inject.Inject;

public class DirectEnergyCon implements EnergyConnection {
    @Inject
    ResourceManagement resourceManagement;

    private int energyUse;

    public DirectEnergyCon(Type type) {
        energyUse = type.getEnergyUse();
    }

    @Override
    public boolean hasEnergy() {
        return resourceManagement.hasResources(energyUse);
    }

    @Override
    public void useEnergy() {
        resourceManagement.useEnergy(energyUse);
    }

    @Override
    public void transferEnergy(int amount) {
        resourceManagement.addEnergy(amount);
    }

}
