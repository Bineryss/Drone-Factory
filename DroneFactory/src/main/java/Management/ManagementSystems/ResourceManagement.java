package Management.ManagementSystems;

import Management.Resources.Resource;

public class ResourceManagement {

    private static final Resource energy = new Resource("Energy", 1000);
    private static final Resource resource = new Resource("Carbon", 1000);


    public static Resource getResource() {
        return resource;
    }

    public static int removeResources(int cost) {
        resource.removeResources(cost);
        return cost;
    }

    public static void addResources(int added) {
        resource.addResources(added);
    }

    public static boolean hasResources(int count) {
        return resource.hasResources(count);
    }

    public static Resource getEnergy() {
        return energy;
    }

    public static int useEnergy(int ammount) {
        return energy.removeResources(ammount);
    }

    public static void addEnergy(int ammount) {
        energy.addResources(ammount);
    }

    public static String resourceName() {
        return resource.NAME;
    }

    public static String print() {
        StringBuilder str = new StringBuilder();
        str.append(energy.toString());
        str.append(resource.toString());
        return str.toString();
    }
}
