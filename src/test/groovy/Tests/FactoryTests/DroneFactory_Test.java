package Tests.FactoryTests;

import ImportandEnums.DroneTypes;
import ImportandEnums.ResourceConnectionsEnum;
import management.ManagementSystems.BuildingManagement;
import management.ManagementSystems.DroneManagement;
import management.ManagementSystems.ResourceManagement;
import production.Factories.Connector.InternalStorage;
import production.Factories.Produktion.Dronefactory.DroneFactory;
import production.Factories.Produktion.Dronefactory.DronefactoryUi;
import specificexceptions.*;
import org.junit.Before;
import org.junit.Test;

public class DroneFactory_Test extends BuildingTest_Setup {
    private DroneFactory droFac;
    private DronefactoryUi ui;

    @Before
    public void start() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException, MissingTransportDrone {
        ResourceManagement.addEnergy(1000);
        ResourceManagement.addResources(1000);
        ui = new DronefactoryUi();

        addDrones(DroneTypes.DEFAULTDRONE, 5);

        droFac = new DroneFactory();
        BuildingManagement.addBuilding(droFac);
        ui.loadInformation(0);

        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 4);
        droFac.update();
        droFac.update();
        droFac.update();
        droFac.update();
        droFac.update();
        try {
            droFac.connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE);
            InternalStorage connection = (InternalStorage) droFac.getStorage();
            connection.addTransportDrone(DroneTypes.DEFAULTDRONE);
            droFac.loadResources(50);
            droFac.getEnergy().loadEnergy(100);
        } catch (BuildingUnfinishedException | NotEnoughStorageException e) {
            System.out.printf("%s%n", e.getMessage());
            assert false;
        }
        System.out.printf("Vor den Tests: %s%n", ui.drawIcon());

        loadBuilding(droFac);

        System.out.printf("Nach dem Aufladen: %s%n", ui.drawIcon());
    }

    @Test
    public void teteDroneFactoryProduce() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException {
        try {
            droFac.startProduction(DroneTypes.DEFAULTDRONE);
        } catch (BuildingUnfinishedException e) {
            assert false;
        }
        System.out.printf("Factory Produziert eine normale Drone: %s%n", ui.drawIcon());
        droFac.update();
        System.out.printf("Factory Produziert eine normale Drone: %s%n", ui.drawIcon());
        droFac.update();
        droFac.update();
        droFac.update();
        System.out.printf("Factory hat eine normale Drone Produziert: %s%n", ui.drawIcon());
    }

    @Test
    public void testFactoryExtension() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException {
        droFac.addDroneProducerExtension(DroneTypes.DEFAULTDRONE);
        System.out.println(ui.drawIcon());
        droFac.activatedProducer();
        DroneManagement.print();
        for (int i = 0; i < 25; i++) {
            droFac.update();
            loadBuilding(droFac, 5);
            DroneManagement.print();
            System.out.printf("%d: Die Factory ist am Produzieren: %s%n", i, ui.drawIcon());
        }
    }

    @Test
    public void testUpdateDroneFactory() throws NotEnoughResourceException, NotEnoughEnergyException, DroneNotEnoughEnergyException {
        DroneManagement.print();
        System.out.println();

        droFac.startConstruction(DroneTypes.DEFAULTDRONE, 4);
        System.out.println(ResourceManagement.print());
        DroneManagement.print();
        System.out.println();
        System.out.println(ui.drawIcon());

        for (int i = 0; i < 5; i++) {
            droFac.update();
        }
        DroneManagement.print();
        loadBuilding(droFac);

        for (int i = 0; i < 3; i++) {
            System.out.println();
            System.out.println(ResourceManagement.print());
            System.out.println(i + ": " + ui.drawIcon());
            droFac.update();
        }
        System.out.println();
        DroneManagement.print();
        System.out.println(ui.drawIcon());
        System.out.println();
    }

}
