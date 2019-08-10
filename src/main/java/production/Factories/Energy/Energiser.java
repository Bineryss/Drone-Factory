package production.Factories.Energy;

import ImportandEnums.BuildingTypes;
import management.ManagementSystems.ResourceManagement;
import production.Factories.Building;
import production.Factories.BuildingInformationElement;
import production.Factories.Connector.EnergyConnection;
import specificexceptions.BuildingUnfinishedException;
import specificexceptions.NotEnoughEnergyException;

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
        int dividableEnergy = connections.size() / dataEntity.getEnergy().availableEnergy();
        for (Building tmp : connections) {
            try {
                EnergyConnection en = tmp.getEnergy();
                en.loadEnergy(dividableEnergy);
            } catch (BuildingUnfinishedException ignored){

            }
        }
    }

    public void loadEnergyInDivider(int amount) throws NotEnoughEnergyException {
        ResourceManagement.useEnergy(dataEntity.getEnergy().loadEnergy(amount));
    }

    public void addBuilding(Building ...buildings) {
        connections.addAll(Arrays.asList(buildings));
    }

    @Override
    public BuildingInformationElement getInformation() {
        return null;
    }
}
