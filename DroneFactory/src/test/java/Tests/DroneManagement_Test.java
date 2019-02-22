package Tests;

import Management.DroneManagement;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DroneManagement_Test {

    @Test
    public void testOnlyOne() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ManagementSystems.xml");
        DroneManagement droM = (DroneManagement) context.getBean("droneManagement");
        System.out.println(droM);
    }
}
