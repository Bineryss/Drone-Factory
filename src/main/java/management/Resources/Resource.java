package management.Resources;

import specificexceptions.NotEnoughResourceException;
import specificexceptions.NotEnoughStorageException;

public class Resource {
    private final String name;

    int maxCapacity;
    int count;


    public Resource(String name, int maxCapacity) {
        this.name = name;
        this.count = 0;
        //TODO: maxCapacity abhaengig von der anzahl an Lagern machen
        this.maxCapacity = maxCapacity;
    }

    public int getAmount() {
        return count;
    }

    public void addResources(int count) throws NotEnoughStorageException {
        if (canStore(count)) {
            this.count += count;
        } else {
            throw new NotEnoughStorageException();
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
        return count <= this.count;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean canStore(int ammount) {
        return maxCapacity >= (this.count + ammount);
    }

    public boolean isFull() {
        return count == maxCapacity;
    }

    public String toString() {
        return String.format("| %s: %3d |", name, count);
    }

    public String getName() {
        return name;
    }
}
