package Production.Dronen.Normal;
import Management.Resources.ResourceCosts;
import Management.Resources.ResourceManagement;
import Production.Dronen.Drone;

/**
 *
 * Eine ganz einfache einstiegs Drone, die nichts besonderes kann, ist aber billig.
 */
public class DefaultDrone extends Drone {

    public DefaultDrone() {
        super();
        id = 0;
        costs = ResourceManagement.generateResourceArray(ResourceCosts.DEFAULTDRONEKOST);
        producitvity = 1;
        energy = 10;
        idelcosts = 1;
        producetime = 4;

        icon = "<|>";
    }

    /**
     *
     * @return: " <|> : Symbol einer Drone und uebrige arbeitskraft.
     */
    public String toString() {
        return icon + " : " + getEnergy();
    }
}
