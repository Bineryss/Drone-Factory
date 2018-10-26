package Management;

public class ResourceManagement {
    private static int RESOURCES;
    private static int ENERGY;


    public static void addRESOURCES(int count) {
        RESOURCES += count;
    }

    public static void addENERGY(int count) {
        ENERGY += count;
    }

    public static int getResorcen() {
        return RESOURCES;
    }

    public static int getEnergy() {
        return ENERGY;
    }

    public static int useResources(int count) {
        if(count <= RESOURCES) {
            RESOURCES -= count;
            return count;
        }
        throw new IllegalArgumentException("So viele Resourcen sind nicht vorhanden!");
    }

    public static int useEnergy(int count) {
        if(count <= ENERGY) {
            ENERGY -= count;
            return count;
        }
        throw new IllegalArgumentException("So viel Energie ist nicht vorhanden!");
    }

    public static String print() {
        return "Energy: " + ENERGY + " | Resources: " + RESOURCES;
    }
}
