import ImportandEnums.Type;
import Management.BuildingManagement;
import Management.DroneManagement;
import Management.Resources.ResourceManagement;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Produktion.DroneFactory;
import org.junit.Before;
import org.junit.Test;

public class DroneFactory_Test {
    private DroneFactory test;

    @Before
    public void start() {
        DroneManagement.start();
        ResourceManagement.start();
        ResourceManagement.addResources(1000);
        ResourceManagement.addEnergy(1000);
        BuildingManagement.start();
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());

        test = new DroneFactory();
        test.startConstruction(Type.DEFAULTDRONE, 4);
        test.update();
        test.update();
        test.update();
        test.update();
        test.update();
        System.out.printf("Vor den Tests: %s%n", test);
        test.loadEnergy(150);
        test.loadResources(50);
        System.out.printf("Nach dem Aufladen: %s%n", test);
    }

    @Test
    public void teteDroneFactoryProduce() {
        test.startProduction(Type.DEFAULTDRONE);
        System.out.printf("Factory Produziert eine normale Drone: %s%n", test);
        test.update();
        System.out.printf("Factory Produziert eine normale Drone: %s%n", test);
        test.update();
        test.update();
        test.update();
        System.out.printf("Factory hat eine normale Drone Produziert: %s%n", test);
    }

    @Test
    public void testFactoryExtension() {
        test.addDroneProducerExtension(Type.DEFAULTDRONE);
        System.out.println(test);
        test.activatedProducer();
        DroneManagement.print();
        for (int i = 0; i < 25; i++) {
            test.update();
            test.loadEnergy(5);
            test.loadResources(2);
            System.out.print(DroneManagement.print());
            System.out.printf("%d: Die Factory ist am Produzieren: %s%n", i, test);
        }
    }


}
