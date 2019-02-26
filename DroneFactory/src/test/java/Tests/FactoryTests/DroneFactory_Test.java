package Tests.FactoryTests;

import ImportandEnums.DroneTypes;
import ImportandEnums.ResourceConnectionsEnum;
import Management.ManagementSystems.BuildingManagement;
import Management.ManagementSystems.DroneManagement;
import Management.ManagementSystems.ResourceManagement;
import Production.Factories.Produktion.DroneFactory;
import SpecificExceptions.BuildingUnfinishedException;
import org.junit.Before;
import org.junit.Test;

public class DroneFactory_Test extends BuildingTest_Setup {
    private DroneFactory droFac;

    @Before
    public void start() {
        addDrones(DroneTypes.DEFAULTDRONE, 5);

        droFac = new DroneFactory();
        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 4);
        droFac.update();
        droFac.update();
        droFac.update();
        droFac.update();
        droFac.update();
        try {
            droFac.connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE);
            droFac.getEnergy().transferEnergy(100);
        } catch (BuildingUnfinishedException e) {
            assert false;
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
            loadBuilding(droFac, 5);
            DroneManagement.print();
            System.out.printf("%d: Die Factory ist am Produzieren: %s%n", i, droFac);
        }
    }

    @Test
    public void testUpdateDroneFactory() {
        DroneManagement.print();
        System.out.println();

        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 4);
        System.out.println(ResourceManagement.print());
        DroneManagement.print();
        System.out.println();
        System.out.println(droFac);

        for (int i = 0; i < 5; i++) {
            droFac.update();
        }
        DroneManagement.print();
        loadBuilding(droFac);

        for (int i = 0; i < 3; i++) {
            System.out.println();
            System.out.println(ResourceManagement.print());
            System.out.println(i + ": " + droFac);
            droFac.update();
        }
        System.out.println();
        DroneManagement.print();
        System.out.println(droFac);
        System.out.println();
    }

}
