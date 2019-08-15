package tests.print;

import ImportandEnums.DroneTypes;
import ImportandEnums.ResourceConnectionsEnum;
import tests.print.BuildingTest_Setup;
import management.ManagementSystems.BuildingManagement;
import org.junit.Before;
import org.junit.Test;
import production.Factories.Connector.InternalStorage;
import production.Factories.Resources.Extractor;
import production.Factories.Resources.ExtractorUi;
import specificexceptions.BuildingUnfinishedException;
import specificexceptions.DroneNotEnoughEnergyException;
import specificexceptions.NoResourceConnection;
import specificexceptions.NotEnoughEnergyException;
import specificexceptions.NotEnoughResourceException;
import specificexceptions.NotEnoughStorageException;

public class ExtractorUiTest extends BuildingTest_Setup {
    private Extractor ext;
    private ExtractorUi ui;

    @Before
    public void setup() {
        super.setup();
        ext = new Extractor();
        BuildingManagement.addBuilding(ext);
        ui = new ExtractorUi(0);
    }

    @Test
    public void testIcon() {
        System.out.println(ui.printIcon());
    }

    @Test
    public void testWindow() {
        System.out.println(ui.openWindow());
    }

    @Test
    public void testInteraction() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException, BuildingUnfinishedException, NotEnoughStorageException, NoResourceConnection {
        ext.startConstruction(DroneTypes.DEFAULTDRONE,4);
        for(int i = 0; i < 3; i++) {
            ext.update();
            System.out.println(ui.printIcon());
        }
        ext.connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE);
        ((InternalStorage) ext.getStorage()).addTransportDrone(DroneTypes.DEFAULTDRONE);
        ext.getEnergy().loadEnergy(100);
        System.out.println(ui.printIcon());
        System.out.println(ui.openWindow());

        for(int i = 0; i < 3; i++) {
            ext.update();
            System.out.println(ui.openWindow());
        }
        System.out.println(ui.printIcon());
        assert "[[|-O| 85E| 15R|Rate: 5]".equals(ui.printIcon());
    }

}
