package Tests.FactoryTests.uiTest;

import ImportandEnums.DroneTypes;
import ImportandEnums.ResourceConnectionsEnum;
import Tests.FactoryTests.BuildingTest_Setup;
import management.ManagementSystems.BuildingManagement;
import org.junit.Before;
import org.junit.Test;
import production.Factories.Connector.InternalStorage;
import production.Factories.Produktion.Dronefactory.DroneFactory;
import production.Factories.Produktion.Dronefactory.DronefactoryUi;
import specificexceptions.BuildingUnfinishedException;
import specificexceptions.DroneNotEnoughEnergyException;
import specificexceptions.MissingTransportDrone;
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
        ui = new DronefactoryUi();
        ui.loadInformation(0);
    }

    @Test
    public void print() {
        System.out.println(ui.drawIcon());
    }

    @Test
    public void printWindow() {
        System.out.println(ui.openWindow());
    }

    @Test
    public void printInteraction() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException, BuildingUnfinishedException, MissingTransportDrone, NotEnoughStorageException {
        addDrones(DroneTypes.DEFAULTDRONE, 5);
        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 4);
        for (int i = 0; i < 5; i++) {
            System.out.println(ui.drawIcon());
            droFac.update();
        }
        System.out.println(ui.drawIcon());
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
    public void switchFactory() throws NotEnoughEnergyException, NotEnoughResourceException, DroneNotEnoughEnergyException {
        addDrones(DroneTypes.DEFAULTDRONE, 5);
        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 5);
        for (int i = 0; i < 5; i++) {
            System.out.println(ui.drawIcon());
            droFac.update();
        }
        System.out.println(ui.drawIcon());

        BuildingManagement.addBuilding(new DroneFactory());
        ui.loadInformation(1);
        System.out.println(ui.drawIcon());
        ui.loadInformation(0);
        System.out.println(ui.drawIcon());

    }
}
