package ImportandEnums;

public enum Type {

    /**
     * Building Costs
     */
    SOLARPANNEL("*~//", 10, 0, 5, 0, 0),

    DRONEFACTORY("[>%]", 50, 100, 20, 10, 200),

    LABORATORIUM("[*]O", 100, 20, 20, 20, 500),

    EXTRACTOR("[|-O", 20, 200, 10, 5, 100),

    VAULT("[__]", 40, 1000, 6,1,100),

    /**
     * Drone Costs
     */
    DEFAULTDRONE("<|>", 10, 10, 4,1,10),

    CARRIERDRONE("<_>", 20, 100, 10,2,10);


    /**
     * Forschungsprojekte kosten
     */

    private final String ICON;

    private int costs;
    private int maxCapacity;
    private int constructionTime;
    private int energyUse;
    private int maxCapacityEnergy;

    Type(String ICON, int costs, int maxCapacity, int constructionTime, int energyUse, int maxCapacityEnergy) {
        this.ICON = ICON;
        this.costs = costs;
        this.maxCapacity = maxCapacity;
        this.constructionTime = constructionTime;
        this.energyUse = energyUse;
        this.maxCapacityEnergy = maxCapacityEnergy;
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

    public String getIcon() {
        return ICON;
    }
}

