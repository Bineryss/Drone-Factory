package Production.Dronen.Normal;

import Management.Resources.Energy;
import Management.Resources.Resource;
import Management.Resources.ResourceCosts;
import Management.Resources.ResourceManagement;
import Management.Resources.Storage;
import Production.Dronen.Drone;

/**
 * Eine ganz einfache einstiegs Drone, die nichts besonderes kann, ist aber billig.
 */
public class DefaultDrone extends Drone {

    public DefaultDrone() {
        super();
        id = 0;
        costs = ResourceCosts.DEFAULTDRONE.getCosts();
        efficiency = 1;
        isOccupied = false;
        energy = new Energy(10, 1, 10);
        resource = new Storage(ResourceCosts.DEFAULTDRONE.getMaxCapacity());


        producetime = ResourceCosts.DEFAULTDRONE.getConstructionTime();

        icon = "<|>";
    }

    /**
     * @return: " <|> : Symbol einer Drone und uebrige arbeitskraft.
     */
    public String toString() {
        return icon + " : " + energyLeft();
    }
}
