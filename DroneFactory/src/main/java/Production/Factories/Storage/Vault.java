package Production.Factories.Storage;

import ImportandEnums.Type;
import Production.Factories.Building;

public class Vault extends Building {
    private static int cc = -1;


    public Vault() {
        super(Type.VAULT);
    }

    @Override
    public void updateBuilding() {

    }
}
