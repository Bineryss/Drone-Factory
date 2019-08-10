package Tests.FactoryTests.uiTest;

import Tests.FactoryTests.BuildingTest_Setup;
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
        ui = new SolarpannelUi();
        ui.loadInformation(0);
    }

    @Test
    public void testIcon() {
        System.out.println(ui.drawIcon());
    }
}
