import Management.Resources.ResourceManagement;
import org.junit.Before;
import org.junit.Test;

public class ResourceManagement_Test {

    @Before
    public void start() {
        ResourceManagement.start();
    }

    @Test
    public void testuseResource() {
        int testadd = 1000;

        ResourceManagement.addResources(testadd);
        ResourceManagement.useResources(200);

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
