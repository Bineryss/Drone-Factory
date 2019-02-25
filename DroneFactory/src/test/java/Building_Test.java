import ImportandEnums.Type;
import Management.*;
import Management.Resources.ResourceManagement;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Building;
import Production.Factories.Connector.Batteries;
import Production.Factories.Connector.InternalStorage;
import Production.Factories.Energy.Solarpannels;
import Production.Factories.Produktion.DroneFactory;
import Production.Factories.Resources.Extractor;
import SpecificExceptions.BuildingUnfinishedException;
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
        loadBuilding(extr);
        try {
        InternalStorage stor = (InternalStorage) extr.getStorage();
        stor.addTransportDrone(Type.DEFAULTDRONE);
        } catch (BuildingUnfinishedException e) {
            assert false;
        }

        System.out.println(ResourceManagement.print() + "\n");
        System.out.println(extr + "\n");

        for (int i = 0; i < 20; i++) {
            extr.update();
            System.out.printf("%2d: %s%n", i, extr);
        }
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
        loadBuilding(dro);

        try{
        dro.startProduction(Type.DEFAULTDRONE);
        }catch (BuildingUnfinishedException e) {
            assert false;
        }

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

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                sol[j].update();
            }
            System.out.println("\nWärend des Baus");
            for (int k = 0; k < 3; k++) {
                System.out.println(sol[k]);
            }
        }

    }

    private void loadBuilding(Building building) {
        try {
            InternalStorage tmp = (InternalStorage) building.getStorage();
            tmp.loadResources(100);
            Batteries bat = (Batteries) building.getEnergy();
            bat.loadEnergy(100);
        } catch (BuildingUnfinishedException e) {
            System.out.println(e.getMessage());
        }

    }
}