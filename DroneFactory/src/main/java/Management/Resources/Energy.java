package Management.Resources;

public class Energy extends Resource {
    private int energyUse;

    /**
     * Speicial Constructor for defining Energy
     *
     * @param maxCapacity
     */
    public Energy(int maxCapacity, int energyUse) {
        super("Energy", -1);

        this.maxCapacity = maxCapacity;
        this.count = 0;
        this.energyUse = energyUse;
    }

    public int loadEnergy(int ammount) {
        if ((ammount + count) > maxCapacity) {
            addResources(maxCapacity);
            return (ammount - maxCapacity);
        } else {
            addResources(ammount);
        }
        return 0;
    }

    public int useEnergy() {
        return useResources(energyUse);
    }

    public boolean hasEnergy() {
        return hasResources(energyUse);
    }

    public int maxCapacity() {
        return maxCapacity;
    }
}
