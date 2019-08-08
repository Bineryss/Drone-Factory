package Tests.FactoryTests;

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
        ResourceManagement.addEnergy(1000);
        ResourceManagement.addResources(1000);
        ResourceManagement.print();

    }

    protected void addDrones(DroneTypes droneTypes, int amount) {
        for (int i = 0; i < amount; i++) {
            DroneManagement.addDrone(new Drone(droneTypes));
        }
    }

    void loadBuilding(Building building, int resources) {
        try {
            InternalStorage tmp = (InternalStorage) building.getStorage();
            tmp.loadResources(resources);
        } catch (BuildingUnfinishedException | NotEnoughStorageException | DroneNotEnoughEnergyException | NotEnoughResourceException | MissingTransportDrone e) {
            System.out.println(e.getMessage());
        }
    }

    void loadBuilding(Building building) {
        loadBuilding(building, 100);
    }
}
