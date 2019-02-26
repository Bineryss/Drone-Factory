package Tests.FactoryTests;

import ImportandEnums.DroneTypes;
import ImportandEnums.ResourceConnectionsEnum;
import Management.ManagementSystems.BuildingManagement;
import Management.ManagementSystems.DroneManagement;
import Production.Factories.Building;
import Production.Factories.Connector.InternalStorage;
import Production.Factories.Produktion.DroneFactory;
import SpecificExceptions.BuildingUnfinishedException;
import org.junit.Before;
import org.junit.Test;

public class DroneFactory_Test extends BuildingTest_Setup{
    private DroneFactory droFac;

    @Before
    public void start() {
        BuildingManagement.start();
        addDrones(DroneTypes.DEFAULTDRONE,5);

        droFac = new DroneFactory();
        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 4);
        droFac.update();
        droFac.update();
        droFac.update();
        droFac.update();
        droFac.update();
        try {
            droFac.connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE);
        } catch (BuildingUnfinishedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Vor den Tests: %s%n", droFac);

        loadBuilding(droFac);
        System.out.printf("Nach dem Aufladen: %s%n", droFac);
    }

    @Test
    public void teteDroneFactoryProduce() {
        try {
            droFac.startProduction(DroneTypes.DEFAULTDRONE);
        } catch (BuildingUnfinishedException e) {
            assert false;
        }
        System.out.printf("Factory Produziert eine normale Drone: %s%n", droFac);
        droFac.update();
        System.out.printf("Factory Produziert eine normale Drone: %s%n", droFac);
        droFac.update();
        droFac.update();
        droFac.update();
        System.out.printf("Factory hat eine normale Drone Produziert: %s%n", droFac);
    }

    @Test
    public void testFactoryExtension() {
        droFac.addDroneProducerExtension(DroneTypes.DEFAULTDRONE);
        System.out.println(droFac);
        droFac.activatedProducer();
        DroneManagement.print();
        for (int i = 0; i < 25; i++) {
            droFac.update();
            loadBuilding(droFac,5);
            System.out.print(DroneManagement.print());
            System.out.printf("%d: Die Factory ist am Produzieren: %s%n", i, droFac);
        }
    }

    private void loadBuilding(Building building, int resources) {
        try {
            InternalStorage tmp = (InternalStorage) building.getStorage();
            tmp.loadResources(resources);
        } catch (BuildingUnfinishedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadBuilding(Building building) {
        loadBuilding(building, 100);
    }

}
