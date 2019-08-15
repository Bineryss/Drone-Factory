package ManagementTest

import management.ManagementSystems.ResourceManagement
import org.junit.Test
import specificexceptions.NotEnoughStorageException

class ResourceManagementTest {
    private void reset() {
        ResourceManagement.removeResources(ResourceManagement.resource.amount)
    }

    @Test
    void testUseResource() {
        reset()
        int testadd = 1000
        ResourceManagement.addResources(testadd)
        ResourceManagement.removeResources(200)

        assert (ResourceManagement.getResource().getAmount() == 800)
    }

    @Test
    void testhaseResources() throws NotEnoughStorageException {
        reset()
        int testadd = 1000
        ResourceManagement.addResources(testadd)
        int test = 500

        assert ResourceManagement.hasResources(test)
    }

    @Test
    void testprint() {
        reset()
        ResourceManagement.print()
    }
}
