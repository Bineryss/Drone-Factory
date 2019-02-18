import Management.*;
import Management.Resources.ResourceManagement;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Energy.Solarpannels;
import Production.Factories.Produktion.DroneFactory;
import Production.Factories.Resources.Extractor;
import org.junit.Before;
import org.junit.Test;

public class Building_Test {

    @Before
    public void start() {
        ResourceManagement.start();
        ResourceManagement.addEnergy(500);
        ResourceManagement.addResources(500);
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
        Extractor extr = new Extractor();
        extr.startConstruction(Type.DEFAULTDRONE, 2);
        for (int i = 0; i < 6; i++) {
            extr.update();
        }
        extr.addDrone(0);
        extr.loadEnergy(500);
        System.out.println(ResourceManagement.print() + "\n");
        System.out.println(extr + "\n");

        for (int i = 0; i < 20; i++) {
            extr.update();
            System.out.printf("%2d: %s%n", i, extr);
        }
        extr.storeResources();
        System.out.println(ResourceManagement.print());
        System.out.println(extr);
        System.out.println();
    }

    @Test
    public void testUpdateSolarpannels() {
        Solarpannels sol = new Solarpannels();
        sol.startConstruction(Type.DEFAULTDRONE, 2);
        System.out.println(ResourceManagement.print());
        System.out.println(sol);

        for (int i = 0; i < 5; i++) {
            sol.update();
            System.out.println(ResourceManagement.print());
            System.out.printf("%2d: %s%n", i, sol);
        }
        System.out.println(sol);
        System.out.println();
    }

    @Test
    public void testUpdateDroneFactory() {
        DroneFactory dro = new DroneFactory();
        System.out.println(DroneManagement.print());
        System.out.println();

        dro.startConstruction(Type.DEFAULTDRONE, 4);
        System.out.println(ResourceManagement.print());
        System.out.println(DroneManagement.print());
        System.out.println();
        System.out.println(dro);

        for (int i = 0; i < 5; i++) {
            dro.update();
            System.out.println(DroneManagement.print());
        }
        dro.loadEnergy(100);
        dro.loadResources(100);

        dro.startProduction(new DefaultDrone());
        for (int i = 0; i < 3; i++) {
            System.out.println();
            System.out.println(ResourceManagement.print());
            System.out.println(i + ": " + dro);
            dro.update();
        }
        System.out.println();
        System.out.println(DroneManagement.print());
        System.out.println(dro);
        System.out.println();
    }

    @Test
    public void testMultipleBuildings() {
        Solarpannels sol1 = new Solarpannels();
        Solarpannels sol2 = new Solarpannels();
        Solarpannels sol3 = new Solarpannels();

        Solarpannels[] sol = new Solarpannels[]{sol1, sol2, sol3};
        sol1.startConstruction(Type.DEFAULTDRONE, 2);
        sol2.startConstruction(Type.DEFAULTDRONE, 1);
        sol3.startConstruction(Type.DEFAULTDRONE, 1);

        System.out.println("Vor dem Bauen:\n");
        for (int i = 0; i < 3; i++) {
            System.out.println(sol[i]);
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sol[j].update();
            }
        }

        System.out.println("\nNach dem Bauen:");
        for (int j = 0; j < 3; j++) {
            System.out.println(sol[j]);
        }
    }
}
