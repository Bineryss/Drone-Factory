package ImportandEnums;

public enum DroneTypes {
    /**
     * Drone Costs
     */
    BUILDINGDRONE("{B}",50,100,12,1,100, 5),
    CARRIERDRONE("{C}",20,100,10,1,20, 0),
    DEFAULTDRONE("{D}",10,10,4,1,10, 1);


    private final String ICON;

    private final int costs;
    private final int maxCapacity;
    private final int constructionTime;
    private final int energyUse;
    private final int maxCapacityEnergy;
    private final int efficiency;

    DroneTypes(String ICON, int costs, int maxCapacity, int constructionTime, int energyUse, int maxCapacityEnergy, int efficiency) {
        this.ICON = ICON;
        this.costs = costs;
        this.maxCapacity = maxCapacity;
        this.constructionTime = constructionTime;
        this.energyUse = energyUse;
        this.maxCapacityEnergy = maxCapacityEnergy;
        this.efficiency = efficiency;
    }

    public int getCosts() {
        return costs;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getConstructionTime() {
        return constructionTime;
    }

    public int getEnergyUse() {
        return energyUse;
    }

    public int getMaxCapacityEnergy() {
        return maxCapacityEnergy;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public String getIcon() {
        return ICON;
    }
}
