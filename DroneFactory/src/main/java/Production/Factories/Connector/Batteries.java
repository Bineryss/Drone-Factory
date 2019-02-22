package Production.Factories.Connector;

import ImportandEnums.Type;
import Management.Resources.Energy;

public class Batteries implements EnergyConnection {
    private Energy energy;

    public Batteries(Type type) {
        this.energy = new Energy(type.getMaxCapacityEnergy(), type.getEnergyUse());
    }

    @Override
    public void transferEnergy(int amount) {
        if (energy.canStore(amount)) {
            energy.loadEnergy(amount);
        } else {
            System.out.println("So viel Energie kann nicht gelagert werden!");
        }
    }

    @Override
    public boolean hasEnergy() {
        return energy.hasEnergy();
    }

    @Override
    public void useEnergy() {
        energy.useEnergy();
    }

    public void loadEnergy(int amount) {
        energy.loadEnergy(amount);
    }

    @Override
    public String toString() {
        return energy.toString();
    }
}
