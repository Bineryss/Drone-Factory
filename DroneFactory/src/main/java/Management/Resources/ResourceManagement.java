package Management.Resources;

public class ResourceManagement {

    private static Resource energy;
    private static Resource resource;

    private ResourceManagement() {
        //mehr Resorce Typen hinzufuegen
        energy = new Resource("Energy", 1000);
        resource = new Resource("Carbon", 1000);
    }

    public Resource getResource() {
        return resource;
    }

    public int removeResources(int cost) {
        resource.removeResources(cost);
        return cost;
    }

    public void addResources(int added) {
        resource.addResources(added);
    }

    public boolean hasResources(int count) {
        return resource.hasResources(count);
    }

    public Resource getEnergy() {
        return energy;
    }

    public int useEnergy(int ammount) {
        return energy.removeResources(ammount);
    }

    public void addEnergy(int ammount) {
        energy.addResources(ammount);
    }

    public String resourceName() {
        return resource.NAME;
    }

    public String toString() {
        return energy.toString() + resource.toString();
    }

}
