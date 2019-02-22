package config;

import Management.Resources.ResourceManagement;
import SpecificExceptions.DuplicatManagementSystemException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("Management")
public class Config {

    @Bean
    ResourceManagement resourceManagement() {
        try {
            return new ResourceManagement();
        } catch (DuplicatManagementSystemException e) {
            e.printStackTrace();
        }
        return null;
    }
}
