package production.Factories.Energy.Solarpannel;

import ImportandEnums.BuildingTypes;
import production.Factories.BuildingDataEntity;
import production.Factories.BuildingUi;

public class SolarpannelUi extends BuildingUi<BuildingDataEntity> {
    private static final String ICON = "*~//";

    @Override
    protected BuildingTypes getType() {
        return BuildingTypes.SOLARPANNEL;
    }

    @Override
    public String drawIcon() {
        StringBuilder out = new StringBuilder();
        out.append("[" + ICON + "|");
        out.append("Energy: " + information.getEfficiency() + "]");
        return out.toString();
    }

    @Override
    protected String drawWindow() {
        return null;
    }

    @Override
    protected String getIcon() {
        return ICON;
    }
}
