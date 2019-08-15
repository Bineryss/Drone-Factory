package tests;

import ImportandEnums.DroneTypes;
import management.ManagementSystems.DroneManagement;
import production.Dronen.Drone;
import specificexceptions.DroneNotEnoughEnergyException;
import org.junit.Before;
import org.junit.Test;

public class DroneManagementTest {
    private Drone testDrone;
    @Before
    public void setup() {
        testDrone = new Drone(DroneTypes.DEFAULTDRONE);
        management.ManagementSystems.DroneManagement.addDrone(testDrone);
    }

    @Test
    public void testAddDrone() {
        Drone compare = new Drone(DroneTypes.CARRIERDRONE);
        management.ManagementSystems.DroneManagement.addDrone(compare);

        assert !(management.ManagementSystems.DroneManagement.getDrone(DroneTypes.DEFAULTDRONE)).equals(null);
    }

    @Test
    public void testGetFullDrone() throws DroneNotEnoughEnergyException {
        Drone testDroneHalf = new Drone(DroneTypes.DEFAULTDRONE);
        testDroneHalf.workEfficiency();
        management.ManagementSystems.DroneManagement.addDrone(testDroneHalf);

        assert testDrone.equals(management.ManagementSystems.DroneManagement.getFullDrone(DroneTypes.DEFAULTDRONE));
    }

    @Test
    public void testGiveDronesWork() {
        assert management.ManagementSystems.DroneManagement.giveDronesWork(testDrone.getType(),1).get(0).hasWorkToDo();
    }

    @Test
    public void testPrint() {
        management.ManagementSystems.DroneManagement.addDrone( new Drone(DroneTypes.BUILDINGDRONE));
        management.ManagementSystems.DroneManagement.print();
    }
}
