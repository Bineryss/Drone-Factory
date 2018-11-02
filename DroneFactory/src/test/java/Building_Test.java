import Management.*;
import Management.Resources.ResourceManagement;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Energy.Solarpannels;
import Production.Factories.Produktion.DroneFactory;
import Production.Factories.Resources.Extractor;
import Production.Factories.Resources.ExtractorTyp;
import org.junit.Before;
import org.junit.Test;

public class Building_Test {

    @Before
    public void start() {
        ResourceManagement.start();
        ResourceManagement.addEnergy(500);
        String resource = "0.400,1.400,2.400";
        ResourceManagement.addResources(ResourceManagement.generateResourceArray(resource));
        DroneManagement.start();
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());
        System.out.println(ResourceManagement.print());
        System.out.println();

    }

    @Test
    public void testUpdateExtractor() {
        Extractor extr = new Extractor(ExtractorTyp.CARBON);
        extr.startConstruction(0,2);
        for(int i = 0; i < 10; i++) {
            extr.update();
        }
        extr.addDrone(0);
        extr.loadEnergy(500);
        System.out.println(ResourceManagement.print());
        System.out.println(extr);

        for(int i = 0; i < 100; i++) {
            extr.update();
            System.out.println(i +  ": " + extr);
        }
        extr.storeResources();
        System.out.println(ResourceManagement.print());
        System.out.println(extr);
        System.out.println();
    }

    @Test
    public void testUpdateSolarpannels() {
        Solarpannels sol = new Solarpannels();
        sol.startConstruction(0,2);
        System.out.println(ResourceManagement.print());
        System.out.println(sol);

        for(int i = 0; i < 5; i++) {
            sol.update();
            System.out.println(ResourceManagement.print());
            System.out.println(i +  ": " + sol);
        }
        System.out.println(sol);
        System.out.println();
    }

    @Test
    public void testUpdateDroneFactory() {
        DroneFactory dro = new DroneFactory();
        System.out.println(DroneManagement.print());
        System.out.println();

        dro.startConstruction(0,2);
        System.out.println(ResourceManagement.print());
        System.out.println(DroneManagement.print());
        System.out.println();
        System.out.println(dro);

        for(int i = 0; i < 5; i++) {
            dro.update();
            System.out.println(DroneManagement.print());
        }
        dro.loadEnergy(100);
        dro.loadResources(ResourceManagement.generateResourceArray("0.100,1.50,2.10"));

        dro.startProduction(new DefaultDrone());
        for(int i = 0; i < 3; i++) {
            System.out.println();
            System.out.println(ResourceManagement.print());
            System.out.println(i +  ": " + dro);
            dro.update();
        }
        System.out.println();
        System.out.println(DroneManagement.print());
        System.out.println(dro);
        System.out.println();
    }
}
