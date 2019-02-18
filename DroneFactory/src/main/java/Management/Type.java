package Management;

public enum Type {

    /**
     * Building Costs
     */
    SOLARPANNEL("*~//",10, 0,5),

    DRONEFACTORY("[>%]",50,100, 20),

    LABORATORIUM("[*]O", 100,20,20),

    EXTRACTOR("[|-O",20,200, 10),

    VAULT("[__]",40,1000,6),

    /**
     * Drone Costs
     */
    DEFAULTDRONE("<|>",10,10, 4),

    CARRIERDRONE("<_>",20,100,10);


    /**
     * Forschungsprojekte kosten
     */

    private final String ICON;

    private int costs;
    private int maxCapacity;
    private int constructionTime;

    Type(String ICON, int costs, int maxCapacity, int constructionTime) {
        this.ICON = ICON;
        this.costs = costs;
        this.maxCapacity = maxCapacity;
        this.constructionTime = constructionTime;
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

    public String getIcon() {
        return ICON;
    }
}

