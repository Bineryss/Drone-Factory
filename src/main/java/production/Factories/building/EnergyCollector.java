package production.Factories.building;

import ImportandEnums.BuildingTypes;
import production.Factories.BuildingDataEntity;
import production.Factories.actionstrategy.GenerateEnergy;
import production.Factories.loadstrategy.NoLoader;

import java.util.Arrays;

public class EnergyCollector extends Building{
    private static int cc = 0;

    EnergyCollector() {
        super(cc, new BuildingDataEntity(BuildingTypes.SOLARPANNEL), Arrays.asList(new NoLoader()),
                Arrays.asList(new GenerateEnergy(BuildingTypes.SOLARPANNEL.getEfficiency())));
        cc++;
    }
}
