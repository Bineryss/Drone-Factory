package Management.Resources;

public enum ResourceCosts {

    /**
     * Building Costs
     */
    SOLARPANNEL(10, 0,5),

    DRONEFACTORY(50,100, 20),

    EXTRACTOR(20,200, 10),

    /**
     * Drone Costs
     */
    DEFAULTDRONE(10,10, 4);


    /**
     * Forschungsprojekte kosten
     */

    private int costs;
    private int maxCapacity;
    private int constructionTime;

    ResourceCosts(int costs, int maxCapacity, int constructionTime) {
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
}

