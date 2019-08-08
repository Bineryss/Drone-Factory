package production.Factories.Storage;

import ImportandEnums.BuildingTypes;
import production.Factories.Building;
import production.Factories.BuildingInformationElement;

public class Vault extends Building {
    private static int cc = -1;


    public Vault() {
        super(BuildingTypes.VAULT);
    }

    @Override
    public void updateBuilding() {

    }

    @Override
    public BuildingInformationElement inform() {
        return null;
    }
}
