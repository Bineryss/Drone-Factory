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
        int[] testadd = ResourceManagement.generateResourceArray("0.10,1.7,2.5");

        ResourceManagement.addResources(testadd);
        ResourceManagement.useResources(ResourceManagement.generateResourceArray("0.8,1.2,2.3"));

        assert (ResourceManagement.getResource(0).resources() == 2);
        assert (ResourceManagement.getResource(1).resources() == 5);
        assert (ResourceManagement.getResource(2).resources() == 2);
    }

    @Test
    public void testhaseResources() {
        int[] testadd = ResourceManagement.generateResourceArray("0.50,1.20,2.10");
        ResourceManagement.addResources(testadd);
        int[] test = ResourceManagement.generateResourceArray("0.50,1.20,2.10");

        assert ResourceManagement.hasResources(test);
    }

    @Test
    public void testgenerateResourceArray() {
        String eingabe = "0.100,2.200";
        int[] erwarte = ResourceManagement.generateResourceArray("0.100,2.200");
        int[] bekommen = ResourceManagement.generateResourceArray(eingabe);
        for(int i = 0; i < erwarte.length; i++) {
            assert (erwarte[i] == bekommen[i]);
        }
    }

    @Test
    public void testprint() {
        ResourceManagement.print();
    }
}
