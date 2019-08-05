package Management.Resources;

import SpecificExceptions.NotEnoughEnergyException;
import SpecificExceptions.NotEnoughResourceException;

public class Energy extends Resource {
    private final int energyUse;

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

    public void loadEnergy(int amount) {
        if ((amount + count) > maxCapacity) {
            addResources(maxCapacity);
        } else {
            addResources(amount);
        }
    }

    public void useEnergy() throws NotEnoughEnergyException {
        try {
            removeResources(energyUse);
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
