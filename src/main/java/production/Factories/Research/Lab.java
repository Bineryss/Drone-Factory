package production.Factories.Research;

import ImportandEnums.BuildingTypes;
import production.Factories.Building;
import production.Factories.BuildingInformationElement;

/**
 * Kleines Labor
 * <p>
 * ID: 2
 */
public class Lab extends Building {
    private static int cc = -1;


    public Lab() {
        super(BuildingTypes.LABORATORIUM);
        cc++;
        id = cc;
    }

    @Override
    public void updateBuilding() {

    }

    @Override
    public BuildingInformationElement getInformation() {
        return null;
    }
}
