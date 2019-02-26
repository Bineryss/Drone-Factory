package Production.Factories.Connector;

import Management.ManagementSystems.ResourceManagement;

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
    public void useResources(int amount) {
        ResourceManagement.removeResources(amount);
    }
}
