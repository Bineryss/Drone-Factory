package Management.Resources;

public class Resource {
    public final String NAME;

    protected int maxCapacity;
    protected int count;


    public Resource(String name, int maxCapacity) {
        NAME = name;
        this.count = 0;
        //TODO: maxCapacity abhaengig von der anzahl an Lagern machen
        this.maxCapacity = maxCapacity;
    }

    public int getResources() {
        return count;
    }

    public void addResources(int count) {
        if (canStore(count)) {
            this.count += count;
        }
    }

    public int useResources(int count) {
        if (hasResources(count)) {
            this.count -= count;
            return count;
        } else {
            throw new IllegalArgumentException("So viele Resourcen hast du nicht!");
        }
    }

    public boolean hasResources(int count) {
        if (count <= this.count) {
            return true;
        } else {
            return false;
        }
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean canStore(int ammount) {
        if (maxCapacity >= (this.count + ammount)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return String.format("| %s: %3d |", NAME, count);
    }
}
