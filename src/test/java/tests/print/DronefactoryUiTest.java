package tests.print;

import ImportandEnums.DroneTypes;
import ImportandEnums.ResourceConnectionsEnum;
import tests.print.BuildingTest_Setup;
import management.ManagementSystems.BuildingManagement;
import org.junit.Before;
import org.junit.Test;
import production.Factories.Connector.InternalStorage;
import production.Factories.Produktion.Dronefactory.DroneFactory;
import production.Factories.Produktion.Dronefactory.DronefactoryUi;
import specificexceptions.BuildingUnfinishedException;
import specificexceptions.DroneNotEnoughEnergyException;
import specificexceptions.MissingTransportDrone;
import specificexceptions.NoResourceConnection;
import specificexceptions.NotEnoughEnergyException;
import specificexceptions.NotEnoughResourceException;
import specificexceptions.NotEnoughStorageException;

public class DronefactoryUiTest extends BuildingTest_Setup {
    private DronefactoryUi ui;
    private DroneFactory droFac;

    @Before
    public void setup() {
        super.setup();
        droFac = new DroneFactory();
        BuildingManagement.addBuilding(droFac);
        ui = new DronefactoryUi(0);
    }

    @Test
    public void print() {
        System.out.println(ui.printIcon());
    }

    @Test
    public void printWindow() {
        System.out.println(ui.openWindow());
    }

    @Test
    public void printInteraction() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException, BuildingUnfinishedException, MissingTransportDrone, NotEnoughStorageException, NoResourceConnection {
        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 4);
        for (int i = 0; i < 5; i++) {
            System.out.println(ui.printIcon());
            droFac.update();
        }
        System.out.println(ui.printIcon());
        droFac.connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE);
        ((InternalStorage) droFac.getStorage()).addTransportDrone(DroneTypes.DEFAULTDRONE);
        droFac.loadResources(100);
        droFac.getEnergy().loadEnergy(100);
        droFac.update();
        System.out.println(ui.openWindow());
        droFac.startProduction(DroneTypes.DEFAULTDRONE);
        System.out.println(ui.openWindow());
        for (int i = 0; i < 4; i++) {
            System.out.println(ui.openWindow());
            droFac.update();
        }
        System.out.println(ui.openWindow());
    }

    @Test
    public void switchFactory() throws NotEnoughEnergyException, NotEnoughResourceException, DroneNotEnoughEnergyException, NotEnoughStorageException {
        addDrones(DroneTypes.DEFAULTDRONE, 5);
        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 5);
        for (int i = 0; i < 5; i++) {
            System.out.println(ui.printIcon());
            droFac.update();
        }
        System.out.println(ui.printIcon());

        BuildingManagement.addBuilding(new DroneFactory());
        ui.loadInformation(1);
        System.out.println(ui.printIcon());
        ui.loadInformation(0);
        System.out.println(ui.printIcon());

    }
}
