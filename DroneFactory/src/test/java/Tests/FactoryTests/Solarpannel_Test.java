package Tests.FactoryTests;

import ImportandEnums.DroneTypes;
import Management.ManagementSystems.ResourceManagement;
import Production.Factories.Energy.Solarpannels;
import org.junit.Before;
import org.junit.Test;

public class Solarpannel_Test extends BuildingTest_Setup{
    private Solarpannels sol;

    @Before
    public void initialize() {
        setup();
        sol = new Solarpannels();
        sol.startConstruction(DroneTypes.DEFAULTDRONE, 2);
    }


    @Test
    public void testUpdateSolarpannelsPrint() {
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
    public void testUpdate() {
        for (int i = 0; i < 5; i++) {
            sol.update();
        }
        assert (!sol.inConstruction());
    }
}
