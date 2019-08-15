package production.Factories.Connector;

import management.ManagementSystems.ResourceManagement;
import specificexceptions.NotEnoughResourceException;
import specificexceptions.NotEnoughStorageException;

public class DirectResourceCon implements ResourceConnection {

    @Override
    public boolean hasResources(int amount) {
        return ResourceManagement.hasResources(amount);
    }

    @Override
    public boolean isFull() {
        return ResourceManagement.getResource().isFull();
    }

    @Override
    public boolean canStore(int amount) {
        return ResourceManagement.getResource().canStore(amount);
    }

    @Override
    public void storeResources(int amount) throws NotEnoughStorageException {
        ResourceManagement.addResources(amount);
    }

    @Override
    public int inStorage() {
        return 0;
    }

    @Override
    public void useResources(int amount) throws NotEnoughResourceException {
        ResourceManagement.removeResources(amount);
    }
}
