package Production.Factories.Storage;

import ImportandEnums.BuildingTypes;
import Production.Factories.Building;

public class Vault extends Building {
    private static int cc = -1;


    public Vault() {
        super(BuildingTypes.VAULT);
    }

    @Override
    public void updateBuilding() {

    }
}
