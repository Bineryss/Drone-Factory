package building

import ImportandEnums.BuildingTypes
import ImportandEnums.DroneTypes
import management.ManagementSystems.DroneManagement
import management.ManagementSystems.ResourceManagement
import org.junit.Before
import org.junit.Test
import production.Dronen.Drone
import production.Factories.BuildingDataEntity
import production.Factories.Energy.Solarpannel.Solarpannels

class SolarpannelTest {
    private Solarpannels sol
    private BuildingDataEntity data

    private void reset() {
        ResourceManagement.removeResources(ResourceManagement.resource.amount)
    }

    @Before
    void setup() {
        reset()
        sol = new Solarpannels()
        data = sol.information.dataEntity
    }

    @Test
    void testConnstruction() {
        ResourceManagement.addResources(BuildingTypes.SOLARPANNEL.costs)
        DroneManagement.addDrone(new Drone(DroneTypes.DEFAULTDRONE))

        sol.startConstruction(DroneTypes.DEFAULTDRONE, 1)
        for (int i = 0; i < BuildingTypes.SOLARPANNEL.constructionTime; i++) {
            assert data.construction == BuildingTypes.SOLARPANNEL.constructionTime - i
            sol.update()
        }
        assert data.construction == 0
        assert ResourceManagement.resource.amount == 0
    }

    @Test
    void testProduction() {
        data.construction = 0
        def energyammount = ResourceManagement.energy
        sol.update()
        assert energyammount.amount == BuildingTypes.SOLARPANNEL.efficiency
    }

    @Test
    void testUnfinshiedBuilding() {
        assert data.construction == BuildingTypes.SOLARPANNEL.constructionTime
        def energyammount = ResourceManagement.energy
        sol.update()
        assert energyammount.amount == 0
    }
}
