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
    public final String ICON = "*~//";
    private static int cc = -1;

    public Solarpannels() {
        super();
        cc++;
        id = 0;
        sid = cc;

        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        constructionCost = ResourceManagement.generateResourceArray(ResourceCosts.SOLARPANNELSCOSTS);
        construction = 5;

        efficency = 10;
    }

    public void update() {
        if (isReady()) {
            ResourceManagement.addEnergy(efficency);
        }
    }

    public String toString() {
        return "[ " + ICON + " , E: " + efficency + " ]" + constructionStatus();
    }
}
