package Tests;

import ImportandEnums.DroneTypes;
import management.ManagementSystems.DroneManagement;
import production.Dronen.Drone;
import specificexceptions.DroneNotEnoughEnergyException;
import org.junit.Before;
import org.junit.Test;

public class DroneManagement_Test {
    private Drone testDrone;
    @Before
    public void setup() {
        testDrone = new Drone(DroneTypes.DEFAULTDRONE);
        DroneManagement.addDrone(testDrone);
    }

    @Test
    public void testAddDrone() {
        Drone compare = new Drone(DroneTypes.CARRIERDRONE);
        DroneManagement.addDrone(compare);

        assert !(DroneManagement.getDrone(DroneTypes.DEFAULTDRONE)).equals(null);
    }

    @Test
    public void testGetFullDrone() throws DroneNotEnoughEnergyException {
        Drone testDroneHalf = new Drone(DroneTypes.DEFAULTDRONE);
        testDroneHalf.workEfficiency();
        DroneManagement.addDrone(testDroneHalf);

        assert testDrone.equals(DroneManagement.getFullDrone(DroneTypes.DEFAULTDRONE));
    }

    @Test
    public void testGiveDronesWork() {
        assert DroneManagement.giveDronesWork(testDrone.getType(),1).get(0).hasWorkToDo();
    }

    @Test
    public void testPrint() {
        DroneManagement.addDrone( new Drone(DroneTypes.BUILDINGDRONE));
        DroneManagement.print();
    }
}
