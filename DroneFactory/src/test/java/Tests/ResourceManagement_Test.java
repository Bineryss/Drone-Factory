package Tests;

import Management.ManagementSystems.ResourceManagement;
import SpecificExceptions.NotEnoughResourceException;
import org.junit.Test;

public class ResourceManagement_Test {

    @Test
    public void testuseResource() throws NotEnoughResourceException {
        int testadd = 1000;
        ResourceManagement.addResources(testadd);
        ResourceManagement.removeResources(200);

        assert (ResourceManagement.getResource().getResources() == 800);

    }

    @Test
    public void testhaseResources() {
        int testadd = 1000;
        ResourceManagement.addResources(testadd);
        int test = 500;

        assert ResourceManagement.hasResources(test);
    }

    @Test
    public void testprint() {
        ResourceManagement.print();
    }
}
