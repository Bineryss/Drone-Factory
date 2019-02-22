package Management;

import Management.Resources.ResourceManagement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class ManagementInjectComponent {

    private Object getSystem(String name) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ManagementSystems.xml");
        return context.getBean(name);
    }

    @Bean("droneManagement")
    public DroneManagement droneManagement() {
        return (DroneManagement) getSystem("drone");
    }

    @Bean("buildingManagement")
    public BuildingManagement buildingManagement() {
        return (BuildingManagement) getSystem("building");
    }

    @Bean("resourceManagement")
    public ResourceManagement resourceManagement() {
        return (ResourceManagement) getSystem("resource");
    }

}
