package Tests.FactoryTests;

import ImportandEnums.DroneTypes;
import Management.ManagementSystems.DroneManagement;
import Management.ManagementSystems.ResourceManagement;
import Production.Dronen.Drone;
import org.junit.Before;
import org.junit.Test;

public class BuildingTest_Setup {

    @Before
    public void setup() {
        ResourceManagement.addEnergy(1000);
        ResourceManagement.addResources(1000);
        ResourceManagement.print();

    }

    @Test
    public void printRescuers() {

    }

    protected void addDrones(DroneTypes droneTypes, int amount) {
        for (int i = 0; i < amount; i++) {
            DroneManagement.addDrone(new Drone(droneTypes));
        }
    }
}
