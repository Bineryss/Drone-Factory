package Tests.FactoryTests;

import ImportandEnums.DroneTypes;
import Management.ManagementSystems.ResourceManagement;
import Production.Factories.Energy.Solarpannels;
import org.junit.Test;

public class Solarpannel_Test {

    @Test
    public void testUpdateSolarpannels() {
        Solarpannels sol = new Solarpannels();
        sol.startConstruction(DroneTypes.DEFAULTDRONE, 2);
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
}
