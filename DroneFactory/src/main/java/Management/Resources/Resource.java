package Management.Resources;

import SpecificExceptions.NotEnoughResourceException;

public class Resource {
    private String name;

    protected int maxCapacity;
    protected int count;


    public Resource(String name, int maxCapacity) {
        this.name = name;
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

    public int removeResources(int count) throws NotEnoughResourceException {
        if (hasResources(count)) {
            this.count -= count;
            return count;
        } else {
            throw new NotEnoughResourceException();
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

    public boolean canStore(int ammount) {
        if (maxCapacity >= (this.count + ammount)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        return count == maxCapacity;
    }

    public String toString() {
        return String.format("| %s: %3d |", name, count);
    }
}
