package Management.Resources;

/**
 * Resourcen Speicher
 *
 */
public class Storage extends Resource{


    public Storage(int maxCapacity) {
        super("Resource",maxCapacity);
    }

    public boolean isFull() {
        return count == maxCapacity;
    }

}
