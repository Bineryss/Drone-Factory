package Tests;

import Management.Resources.ResourceManagement;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceManagement_Test {
    ResourceManagement resourceManagement;

    @Before
    public void setup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ManagementSystems.xml");
        resourceManagement = (ResourceManagement) context.getBean("resourceManagement");
    }

    @Test
    @DisplayName("Es wird getested ob 200 Resourcen verbracuht werden")
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
