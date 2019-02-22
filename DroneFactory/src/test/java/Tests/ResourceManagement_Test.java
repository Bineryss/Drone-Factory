package Tests;

import Management.Resources.ResourceManagement;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;

import javax.inject.Inject;

@ComponentScan("Management.Resources")
public class ResourceManagement_Test {
    @Inject
    ResourceManagement resourceManagement;

    @Test
    public void testuseResource() {
        int testadd = 1000;

        resourceManagement.addResources(testadd);
        resourceManagement.removeResources(200);

        assert (resourceManagement.getResource().getResources() == 800);

    }

    @Test
    public void testhaseResources() {
        int testadd = 1000;
        resourceManagement.addResources(testadd);
        int test = 500;

        assert resourceManagement.hasResources(test);
    }

    @Test
    public void testprint() {
        System.out.println(resourceManagement);
    }
}
