package Production.Factories.Energy;

import Management.Type;
import Management.Resources.ResourceManagement;
import Production.Factories.Building;

/**
 * Solarpannel - Produziert Energie
 * <p>
 * ID: 0
 */
public class Solarpannels extends Building {
    private static int cc = -1;

    public Solarpannels() {
        super();
        cc++;
        sid = cc;

        type = Type.SOLARPANNEL;

        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        constructionCost = Type.SOLARPANNEL.getCosts();
        construction = Type.SOLARPANNEL.getConstructionTime();

        efficiency = 10;
    }

    public void updateBuilding() {
        if (isReady()) {
            ResourceManagement.addEnergy(efficiency);
        }
    }

    public String toString() {
        return "[ " + type.getIcon() + "  |E: " + efficiency + "| ]" + constructionStatus();
    }
}
