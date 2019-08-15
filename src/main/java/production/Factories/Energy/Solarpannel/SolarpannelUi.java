package production.Factories.Energy.Solarpannel;

import ImportandEnums.BuildingTypes;
import production.Factories.BuildingDataEntity;
import production.Factories.BuildingUi;

public class SolarpannelUi extends BuildingUi<BuildingDataEntity> {

    public SolarpannelUi(int id) {
        super(BuildingTypes.SOLARPANNEL, id);
    }

    @Override
    protected String drawResoucreSpace() {
        return String.format("Energy: %s", information.getEfficiency());

    }

    @Override
    protected String drawInformationSpace() {
        return "";
    }

    @Override
    protected String drawWindow() {
        return null;
    }
}
