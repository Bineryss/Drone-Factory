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
        int[] testadd = new int[]{10,15,5};

        ResourceManagement.addResources(testadd);
        ResourceManagement.useResources(new int[]{8,10,3});

        assert (ResourceManagement.getResource(0).resources() == 2);
        assert (ResourceManagement.getResource(1).resources() == 5);
        assert (ResourceManagement.getResource(2).resources() == 2);
    }

    @Test
    public void testhaseResources() {
        int[] testadd = new int[]{50,20,10};
        ResourceManagement.addResources(testadd);
        int[] test = new int[]{50,20,10};

        assert ResourceManagement.hasResources(test);
    }

    @Test
    public void testgenerateResourceArray() {
        String eingabe = "";
        int[] erwarte = new int[]{0,0,0};
        int[] bekommen = ResourceManagement.generateResourceArray(eingabe);
        for(int i = 0; i < erwarte.length; i++) {
            assert (erwarte[i] == bekommen[i]);
        }
    }

    @Test
    public void testprint() {
        String erwartet = "| Energy: 0 || Carbon: 0 || Graphen: 0 || Cobalt: 0 |";

        assert (erwartet.equals(ResourceManagement.print()));
    }
}
