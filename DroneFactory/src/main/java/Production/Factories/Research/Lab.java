package Production.Factories.Research;

import ImportandEnums.BuildingTypes;
import Production.Factories.Building;

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

    public String toString() {
        return "[ " + buildingTypes.getIcon() + " ]" + constructionStatus();
    }

}
