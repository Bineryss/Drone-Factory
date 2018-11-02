package Management.Resources;

public class ResourceManagement {
    public static final int RESOURCESCOUNT = 8;

    private static Resource energy;
    private static Resource[] resources = new Resource[RESOURCESCOUNT];


    public static void start() {
        energy = new Resource("Energy", -1);
        //TODO: mehr Resorce Typen hinzufuegen
        resources[0] = new Resource("Carbon", 0);
        resources[1] = new Resource("Silicon",1);
        resources[2] = new Resource("Graphen", 2);
        resources[3] = new Resource("Cobalt", 3);
        resources[4] = new Resource("Deuterium", 4);
        resources[5] = new Resource("Helium - 3", 5);
        resources[6] = new Resource("Magnetit", 6);
        resources[7] = new Resource("Iridium", 7);

    }

    public static Resource[] getResources() {
        return resources;
    }

    public static Resource getResource(int id) {
        return resources[id];
    }

    public static int[] useResources(int[] costs) {
        for (int i = 0; i < resources.length; i++) {
            resources[i].useResources(costs[i]);
        }
        return costs;
    }

    public static int useResources(int id, int costs) {
        return resources[id].useResources(costs);
    }

    public static void addResources(int[] added) {
        for (int i = 0; i < resources.length; i++) {
            resources[i].addResources(added[i]);
        }
    }

    public static boolean hasResources(int[] count) {
        for (int i = 0; i < resources.length; i++) {
            if (!resources[i].hasResources(count[i])) {
                return false;
            }
        }
        return true;
    }


    public static int[] generateResourceArray(String input) {
        int[] output = new int[RESOURCESCOUNT];
        if (!input.equals("")) {
            String[] splittByTyp = input.split(",");
            for (int i = 0; i < splittByTyp.length; i++) {
                String[] tmpSplit = splittByTyp[i].split("\\.");
                int[] tmp = new int[]{Integer.parseInt(tmpSplit[0]), Integer.parseInt(tmpSplit[1])};
                output[tmp[0]] = tmp[1];
            }
        }
        return output;
    }


    public static Resource getEnergy() {
        return energy;
    }

    public static int useEnergy(int ammount) {
        return energy.useResources(ammount);
    }

    public static void addEnergy(int ammount) {
        energy.addResources(ammount);
    }

    public static boolean hasEnergy(int ammount) {
        return energy.hasResources(ammount);
    }

    public static String resourceName(int id) {
        return resources[id].NAME;
    }

    public static String print() {
        StringBuilder str = new StringBuilder();
        str.append(energy.toString());
        for (int i = 0; i < resources.length; i++) {
            str.append(resources[i].toString());
        }
        return str.toString();
    }

}
