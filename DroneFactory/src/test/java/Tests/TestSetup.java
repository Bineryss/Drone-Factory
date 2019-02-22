package Tests;

import Management.BuildingManagement;
import Management.DroneManagement;
import Management.Resources.ResourceManagement;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSetup {
    protected ResourceManagement resourceManagement;
    protected DroneManagement droneManagement;
    protected BuildingManagement buildingManagement;


    @Before
    public void setup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ManagementSystems.xml");
        resourceManagement = (ResourceManagement) context.getBean("resource");
        droneManagement = (DroneManagement) context.getBean("drone");
        buildingManagement = (BuildingManagement) context.getBean("building");
    }
}
