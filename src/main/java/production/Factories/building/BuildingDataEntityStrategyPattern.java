package production.Factories.building;

import ImportandEnums.BuildingTypes;
import management.Resources.Resource;

public class BuildingDataEntityStrategyPattern {
    private int efficiency;

    private Resource materialStorage;
    private Resource energyStorage;

    public BuildingDataEntityStrategyPattern(BuildingTypes type) {
        efficiency = type.getEfficiency();

    }
}
