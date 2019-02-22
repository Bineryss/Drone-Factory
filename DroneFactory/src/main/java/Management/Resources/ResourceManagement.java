package Management.Resources;

import SpecificExceptions.DuplicatManagementSystemException;

import javax.inject.Named;

@Named("resourceManagement")
public class ResourceManagement {
    private static boolean IS_ACTIVE = false;

    private static Resource energy;
    private static Resource resource;

    public ResourceManagement() throws DuplicatManagementSystemException {
        if (!IS_ACTIVE) {
            //mehr Resorce Typen hinzufuegen
            energy = new Resource("Energy", 1000);
            resource = new Resource("Carbon", 1000);
            IS_ACTIVE = true;
        } else {
            throw new DuplicatManagementSystemException();
        }


    }

    public Resource getResource() {
        return resource;
    }

    public int removeResources(int cost) {
        resource.removeResources(cost);
        return cost;
    }

    public void addResources(int added) {
        resource.addResources(added);
    }

    public boolean hasResources(int count) {
        return resource.hasResources(count);
    }

    public Resource getEnergy() {
        return energy;
    }

    public int useEnergy(int ammount) {
        return energy.removeResources(ammount);
    }

    public void addEnergy(int ammount) {
        energy.addResources(ammount);
    }

    public String resourceName() {
        return resource.NAME;
    }

    public String toString() {
        return energy.toString() + resource.toString();
    }

}
