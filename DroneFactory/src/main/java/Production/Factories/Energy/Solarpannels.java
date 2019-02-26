package Production.Factories.Energy;

import ImportandEnums.BuildingTypes;
import Management.ManagementSystems.ResourceManagement;
import Production.Factories.Building;

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

    private void generateEnergy() {
        ResourceManagement.addEnergy(efficiency);
    }

    public String toString() {
        return "[ " + buildingTypes.getIcon() + " || Energy: " + efficiency + "||]" + constructionStatus();
    }
}
