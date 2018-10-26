package Production.Factories.Energy;

import Management.ResourceManagement;
import Production.Factories.Building;

/**
 *
 * Solarpannel - Produziert Energie
 *
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
        costs = 5;
        construction = 5;

        efficency = 10;
    }

    public void update() {
        if(isReady()) {
            if(energyStorable >= (energy + efficency)) {
                ResourceManagement.addENERGY(efficency);
            }
        }
    }

    public String toString() {
        return "[ " + ICON + " , E: " + efficency + " ]" + constructionStatus();
    }
}
