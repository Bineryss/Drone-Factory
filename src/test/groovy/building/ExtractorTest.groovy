package building

import ImportandEnums.BuildingTypes
import ImportandEnums.DroneTypes
import ImportandEnums.ResourceConnectionsEnum
import management.ManagementSystems.DroneManagement
import management.ManagementSystems.ResourceManagement
import org.junit.Before
import org.junit.Test
import production.Dronen.Drone
import production.Factories.BuildingDataEntity
import production.Factories.Resources.Extractor

class ExtractorTest {
    private Extractor ext
    private BuildingDataEntity data

    private void reset() {
        ResourceManagement.removeResources(ResourceManagement.resource.amount)
    }

    @Before
    void setup() {
        reset()
        ext = new Extractor()
        data = ext.information.dataEntity
    }

    @Test
    void testConnstruction() {
        ResourceManagement.addResources(BuildingTypes.EXTRACTOR.costs)
        DroneManagement.addDrone(new Drone(DroneTypes.DEFAULTDRONE))

        ext.startConstruction(DroneTypes.DEFAULTDRONE, 1)
        for (int i = 0; i < BuildingTypes.EXTRACTOR.constructionTime; i++) {
            ext.update()
        }
        assert ResourceManagement.resource.amount == 0
        assert data.construction == 0
    }

    @Test
    void testProduction() {
        final int rounds = 5
        ResourceManagement.addEnergy(rounds * BuildingTypes.EXTRACTOR.energyUse)

        assert ResourceManagement.resource.amount == 0
        data.construction = 0

        ext.connectStorage(ResourceConnectionsEnum.DIRECTRESOURCECONNECT)
        ext.getEnergy().loadEnergy(rounds * BuildingTypes.EXTRACTOR.energyUse)

        assert ResourceManagement.energy.amount == 0

        for (int i; i < rounds; i++) {
            ext.update()
        }

        assert data.energy.availableEnergy() == 0
        assert ResourceManagement.resource.amount == rounds * BuildingTypes.EXTRACTOR.efficiency
    }
}
