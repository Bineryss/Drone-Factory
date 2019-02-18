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

    public boolean canStoreResources(int ammount) {
        return addResources(ammount);
    }

    public int unload() {
        int tmp = count;
        count = 0;
        return tmp;
    }
}
