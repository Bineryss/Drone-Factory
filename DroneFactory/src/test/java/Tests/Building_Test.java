package Tests;

import ImportandEnums.DroneTypes;
import Management.ManagementSystems.ResourceManagement;
import Production.Factories.Energy.Solarpannels;
import Tests.FactoryTests.BuildingTest_Setup;
import org.junit.Before;
import org.junit.Test;

public class Building_Test extends BuildingTest_Setup {

    @Before
    public void start() {
        addDrones(DroneTypes.DEFAULTDRONE,5);
        System.out.println(ResourceManagement.print());
        System.out.println();

    }

    @Test
    public void testMultipleBuildings() {
        Solarpannels sol1 = new Solarpannels();
        Solarpannels sol2 = new Solarpannels();
        Solarpannels sol3 = new Solarpannels();

        Solarpannels[] sol = new Solarpannels[]{sol1, sol2, sol3};
        sol1.startConstruction(DroneTypes.DEFAULTDRONE, 2);
        sol2.startConstruction(DroneTypes.DEFAULTDRONE, 1);
        sol3.startConstruction(DroneTypes.DEFAULTDRONE, 1);

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
}
