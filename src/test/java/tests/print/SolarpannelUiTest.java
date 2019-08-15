package tests.print;

import management.ManagementSystems.BuildingManagement;
import org.junit.Before;
import org.junit.Test;
import production.Factories.Energy.Solarpannel.SolarpannelUi;
import production.Factories.Energy.Solarpannel.Solarpannels;

public class SolarpannelUiTest extends BuildingTest_Setup {
    private Solarpannels sol;
    private SolarpannelUi ui;

    @Before
    public void setup() {
        super.setup();
        sol = new Solarpannels();
        BuildingManagement.addBuilding(sol);
        ui = new SolarpannelUi(0);
    }

    @Test
    public void testIcon() {
        System.out.println(ui.printIcon());
    }

    @Test
    public void testWindow() {
        System.out.println(ui.openWindow());
    }
}
