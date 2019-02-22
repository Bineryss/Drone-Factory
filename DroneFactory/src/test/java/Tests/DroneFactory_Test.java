package Tests;

import ImportandEnums.EnergyConnectionEnum;
import ImportandEnums.ResourceConnectionsEnum;
import ImportandEnums.Type;
import Management.BuildingManagement;
import Management.DroneManagement;
import Management.Resources.ResourceManagement;
import Production.Factories.Building;
import Production.Factories.Connector.Batteries;
import Production.Factories.Connector.InternalStorage;
import Production.Factories.Produktion.DroneFactory;
import SpecificExceptions.BuildingUnfinishedException;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

public class DroneFactory_Test {
    @Inject
    DroneManagement droneManagement;
    @Inject
    BuildingManagement buildingManagement;
    @Inject
    ResourceManagement resourceManagement;

    private DroneFactory droFac;

    @Before
    public void start() {
        resourceManagement.addResources(1000);
        resourceManagement.addEnergy(1000);
        droneManagement.addDrone(new DefaultDrone());
        droneManagement.addDrone(new DefaultDrone());
        droneManagement.addDrone(new DefaultDrone());
        droneManagement.addDrone(new DefaultDrone());

        droFac = new DroneFactory();
        droFac.startConstruction(Type.DEFAULTDRONE, 4);
        droFac.update();
        droFac.update();
        droFac.update();
        droFac.update();
        droFac.update();
        try {
            droFac.connectEnergy(EnergyConnectionEnum.BATTERIES);
            droFac.connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE);
        } catch (BuildingUnfinishedException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Vor den Tests: %s%n", droFac);

        loadBuilding(droFac);
        System.out.printf("Nach dem Aufladen: %s%n", droFac);
    }

    @Test
    public void teteDroneFactoryProduce() {
        try {
            droFac.startProduction(Type.DEFAULTDRONE);
        } catch (BuildingUnfinishedException e) {
            assert false;
        }
        System.out.printf("Factory Produziert eine normale Drone: %s%n", droFac);
        droFac.update();
        System.out.printf("Factory Produziert eine normale Drone: %s%n", droFac);
        droFac.update();
        droFac.update();
        droFac.update();
        System.out.printf("Factory hat eine normale Drone Produziert: %s%n", droFac);
    }

    @Test
    public void testFactoryExtension() {
        droFac.addDroneProducerExtension(Type.DEFAULTDRONE);
        System.out.println(droFac);
        droFac.activatedProducer();
        System.out.println(droneManagement);
        for (int i = 0; i < 25; i++) {
            droFac.update();
            loadBuilding(droFac,5,5);
            System.out.print(droneManagement);
            System.out.printf("%d: Die Factory ist am Produzieren: %s%n", i, droFac);
        }
    }

    private void loadBuilding(Building building, int energy, int resources) {
        try {
            InternalStorage tmp = (InternalStorage) building.getStorage();
            tmp.loadResources(resources);
            Batteries bat = (Batteries) building.getEnergy();
            bat.loadEnergy(energy);
        } catch (BuildingUnfinishedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadBuilding(Building building) {
        loadBuilding(building, 100, 100);
    }


}
