package Production.Factories.Energy;

import ImportandEnums.BuildingTypes;
import Management.ManagementSystems.ResourceManagement;
import Production.Factories.Building;
import Production.Factories.Connector.EnergyConnection;
import SpecificExceptions.BuildingUnfinishedException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Energiser extends Building {
    private List<Building> connections;

    public Energiser() {
        super(BuildingTypes.ENERGISER);
        connections = new LinkedList<>();
    }

    @Override
    protected void updateBuilding() {
        int dividableEnergy = connections.size() / energy.availableEnergy();
        for (Building tmp : connections) {
            try {
                EnergyConnection en = tmp.getEnergy();
                en.transferEnergy(dividableEnergy);
            } catch (BuildingUnfinishedException ignored){

            }
        }
    }

    public void loadEnergyInDivider(int amount) {
        ResourceManagement.useEnergy(energy.transferEnergy(amount));
    }

    public void addBuilding(Building ...buildings) {
        connections.addAll(Arrays.asList(buildings));
    }
}
