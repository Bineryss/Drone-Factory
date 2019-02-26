package Production.Factories.Energy;

import ImportandEnums.BuildingTypes;
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
    public void updateBuilding() {
        if (isReady()) {
            energy.transferEnergy(efficiency);
        }
    }

    public String toString() {
        return "[ " + buildingTypes.getIcon() + " || Energy: " + efficiency + "||]" + constructionStatus();
    }
}
