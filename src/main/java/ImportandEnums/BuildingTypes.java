package ImportandEnums;

public enum BuildingTypes {

    /**
     * Building Costs
     */
    SOLARPANNEL("*~//", 10, 0, 5, 0, 0, 10),
    ENERGISER("~||~",5,0,2,0,500,0),

    DRONEFACTORY("[>%]", 50, 150, 20, 10, 200, 1),

    LABORATORIUM("[*]O", 100, 20, 20, 20, 500, 2),

    EXTRACTOR("[|-O", 20, 200, 10, 5, 100, 5),

    VAULT("[__]", 40, 1000, 6, 1, 100, 0);

    /**
     * Forschungsprojekte kosten
     */

    private final String ICON;

    private final int costs;
    private final int maxCapacity;
    private final int constructionTime;
    private final int energyUse;
    private final int maxCapacityEnergy;
    private final int efficiency;

    BuildingTypes(String ICON, int costs, int maxCapacity, int constructionTime, int energyUse, int maxCapacityEnergy, int efficiency) {
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

