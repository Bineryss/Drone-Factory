package tests.print;

import ImportandEnums.DroneTypes;
import management.ManagementSystems.*;
import production.Dronen.Drone;
import production.Factories.Building;
import production.Factories.Connector.InternalStorage;
import specificexceptions.*;
import org.junit.Before;

public class BuildingTest_Setup {

    @Before
    public void setup() {
        try {
            ResourceManagement.addEnergy(1000);
            ResourceManagement.addResources(1000);
        } catch (NotEnoughStorageException e) {
            e.printStackTrace();
        }
        addDrones(DroneTypes.DEFAULTDRONE, 5);
        System.out.println(ResourceManagement.print());
        System.out.println(DroneManagement.print());

    }

    protected void addDrones(DroneTypes droneTypes, int amount) {
        for (int i = 0; i < amount; i++) {
            DroneManagement.addDrone(new Drone(droneTypes));
        }
    }

    public void loadBuilding(Building building, int resources) {
        try {
            InternalStorage tmp = (InternalStorage) building.getStorage();
            tmp.loadResources(resources);
        } catch (NotEnoughStorageException | DroneNotEnoughEnergyException | NotEnoughResourceException | MissingTransportDrone | NoResourceConnection e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadBuilding(Building building) {
        loadBuilding(building, 100);
    }
}
