package Production.Factories.Energy;

import Management.Resources.ResourceCosts;
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
        id = 0;
        sid = cc;
        ICON = "*~//";

        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        constructionCost = ResourceCosts.SOLARPANNEL.getCosts();
        construction = ResourceCosts.SOLARPANNEL.getConstructionTime();

        efficency = 10;
    }

    public void updateBuilding() {
        if (isReady()) {
            ResourceManagement.addEnergy(efficency);
        }
    }

    public String toString() {
        return "[ " + ICON + "  |E: " + efficency + "| ]" + constructionStatus();
    }
}
