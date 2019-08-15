package building

import ImportandEnums.BuildingTypes
import ImportandEnums.DroneTypes
import ImportandEnums.ResourceConnectionsEnum
import management.ManagementSystems.DroneManagement
import management.ManagementSystems.ResourceManagement
import org.junit.Before
import org.junit.Test
import production.Dronen.Drone
import production.Factories.Connector.InternalStorage
import production.Factories.Produktion.Dronefactory.DroneFactory
import production.Factories.Produktion.Dronefactory.DronefactoryDataEntity

class DronefactoryTest {
    private DroneFactory dro
    private DronefactoryDataEntity data

    @Before
    void setup() {
        reset()
        dro = new DroneFactory()
        data = dro.information.dataEntity
    }

    @Test
    void testInConnstruction() {
        ResourceManagement.addResources(BuildingTypes.DRONEFACTORY.costs)
        DroneManagement.addDrone(new Drone(DroneTypes.DEFAULTDRONE), new Drone(DroneTypes.DEFAULTDRONE))

        dro.startConstruction(DroneTypes.DEFAULTDRONE, 1)
        for (int i = 0; i < BuildingTypes.DRONEFACTORY.constructionTime / 2; i++) {
            assert data.construction == BuildingTypes.DRONEFACTORY.constructionTime - i
            dro.update()
        }

        dro.addMoreWorkers(DroneTypes.DEFAULTDRONE, 1)
        for (int i = 0; i < BuildingTypes.DRONEFACTORY.constructionTime / 2; i++) {
            assert data.construction == BuildingTypes.DRONEFACTORY.constructionTime / 2 - i
            dro.update()
        }
        assert data.construction == 0
        assert ResourceManagement.resource.amount == 0
    }

    @Test
    void testResourceConnection() {
        int amount = 100
        ResourceManagement.addResources(amount)
        DroneManagement.addDrone(new Drone(DroneTypes.DEFAULTDRONE))
        data.construction = 0

        dro.connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE)
        assert data.storage instanceof InternalStorage

        ((InternalStorage) dro.getStorage()).addTransportDrone(DroneTypes.DEFAULTDRONE)
        assert ((InternalStorage) data.storage).transportDrone != null

        dro.loadResources(amount)

        assert data.storage.inStorage() == amount
        assert ResourceManagement.resource.amount == 0
    }

    @Test
    void testDroneProduction() {
        int amount = DroneTypes.DEFAULTDRONE.costs
        ResourceManagement.addResources(amount)
        ResourceManagement.addEnergy(100)

        data.construction = 0
        dro.connectStorage(ResourceConnectionsEnum.DIRECTRESOURCECONNECT)
        dro.loadResources(amount)
        dro.getEnergy().loadEnergy(100)

        dro.startProduction(DroneTypes.DEFAULTDRONE)
        for (int i = 0; i < DroneTypes.DEFAULTDRONE.constructionTime; i++) {
            assert data.productionStatus == DroneTypes.DEFAULTDRONE.constructionTime - (data.efficiency * i)
            dro.update()
        }
        assert data.producedElement == null
        assert DroneManagement.print().contains(DroneTypes.DEFAULTDRONE.icon)
    }

    @Test
    void testDroneProducer() {
        int amount = DroneTypes.DEFAULTDRONE.costs * 3
        ResourceManagement.addResources(amount)
        ResourceManagement.addEnergy(100)

        data.construction = 0
        dro.connectStorage(ResourceConnectionsEnum.DIRECTRESOURCECONNECT)
        dro.loadResources(amount)
        dro.getEnergy().loadEnergy(100)

        dro.addDroneProducerExtension(DroneTypes.DEFAULTDRONE)
        dro.activatedProducer()
        for (int i = 0; i < DroneTypes.DEFAULTDRONE.constructionTime * 2 + 1; i++) {
            dro.update()
        }

        assert data.prod.drones.size() == 2
    }

    private void reset() {
        ResourceManagement.removeResources(ResourceManagement.resource.amount)
    }
}
