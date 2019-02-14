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

    public boolean addResources(int count) {
        if (maxCapacity >= (this.count + count)) {
            this.count += count;
            return true;
        } else {
            return false;
            //throw new IllegalArgumentException("So viel kannst du nicht lagern!");
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

    public String toString() {
        return "| " + NAME + ": " + count + " |";
    }
}
