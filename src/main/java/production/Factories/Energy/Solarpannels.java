package production.Factories.Energy;

import ImportandEnums.BuildingTypes;
import management.ManagementSystems.ResourceManagement;
import production.Factories.Building;
import production.Factories.BuildingInformationElement;

/**
 * Solarpannel - Produziert Energie
 * <p>
 * ID: 0
 */
public class Solarpannels extends Building {
    private static int cc = -1;

    public Solarpannels() {
        super(BuildingTypes.SOLARPANNEL);
        cc++;
        id = cc;
    }

    @Override
    public void updateBuilding()  {
        if (isReady()) {
            generateEnergy();
        }
    }

    @Override
    public BuildingInformationElement inform() {
        return null;
    }

    private void generateEnergy() {
        ResourceManagement.addEnergy(efficiency);
    }

//    public String toString() {
//        return "[ " + buildingTypes.getIcon() + " || Energy: " + efficiency + "||]" + constructionStatus();
//    }
}