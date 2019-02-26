package Management.Resources;

import SpecificExceptions.NotEnoughEnergyException;
import SpecificExceptions.NotEnoughResourceException;

public class Energy extends Resource {
    private int energyUse;

    public Energy(int maxCapacity, int energyUse) {
        super("Energy", maxCapacity);

        this.energyUse = energyUse;
    }

    /**
     * Special Constructor for Drones only!
     *
     * @param maxCapacity
     * @param energyUse
     * @param placebo
     */
    public Energy(int maxCapacity, int energyUse, int placebo) {
        super("Energy",maxCapacity);

        this.count = maxCapacity;
        this.energyUse = energyUse;
    }

    public int loadEnergy(int amount) {
        if ((amount + count) > maxCapacity) {
            addResources(maxCapacity);
            return (amount - maxCapacity);
        } else {
            addResources(amount);
        }
        return 0;
    }

    public int useEnergy() throws NotEnoughEnergyException {
        try {
            return removeResources(energyUse);
        }catch (NotEnoughResourceException e) {
            throw new NotEnoughEnergyException();
        }
    }

    public boolean hasEnergy() {
        return hasResources(energyUse);
    }

    public int availableEnergy() {
        return count;
    }
}
