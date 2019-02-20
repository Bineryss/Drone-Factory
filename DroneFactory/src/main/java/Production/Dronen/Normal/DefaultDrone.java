package Production.Dronen.Normal;

import Management.Resources.Energy;
import ImportandEnums.Type;
import Management.Resources.Storage;
import Production.Dronen.Drone;

/**
 * Eine ganz einfache einstiegs Drone, die nichts besonderes kann, ist aber billig.
 */
public class DefaultDrone extends Drone {

    public DefaultDrone() {
        super();
        type = Type.valueOf("DEFAULTDRONE");
        type = Type.DEFAULTDRONE;
        costs = Type.DEFAULTDRONE.getCosts();
        efficiency = 1;
        isOccupied = false;
        energy = new Energy(type.getMaxCapacityEnergy(), type.getEnergyUse(), type.getMaxCapacity());
        resource = new Storage(Type.DEFAULTDRONE.getMaxCapacity());


        producetime = Type.DEFAULTDRONE.getConstructionTime();
    }

    /**
     * @return: " <|> : Symbol einer Drone und uebrige arbeitskraft.
     */
    public String toString() {
        return type.getIcon() + " : " + energyLeft();
    }
}
