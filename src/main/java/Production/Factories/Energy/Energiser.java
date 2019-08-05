package Production.Factories.Energy;

import ImportandEnums.BuildingTypes;
import Management.ManagementSystems.ResourceManagement;
import Production.Factories.Building;
import Production.Factories.Connector.EnergyConnection;
import SpecificExceptions.BuildingUnfinishedException;
import SpecificExceptions.NotEnoughEnergyException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Energiser extends Building {
    private final List<Building> connections;

    public Energiser() {
        super(BuildingTypes.ENERGISER);
        connections = new LinkedList<>();
    }

    @Override
    protected void updateBuilding() throws NotEnoughEnergyException {
        int dividableEnergy = connections.size() / energy.availableEnergy();
        for (Building tmp : connections) {
            try {
                EnergyConnection en = tmp.getEnergy();
                en.loadEnergy(dividableEnergy);
            } catch (BuildingUnfinishedException ignored){

            }
        }
    }

    public void loadEnergyInDivider(int amount) throws NotEnoughEnergyException {
        ResourceManagement.useEnergy(energy.loadEnergy(amount));
    }

    public void addBuilding(Building ...buildings) {
        connections.addAll(Arrays.asList(buildings));
    }
}
