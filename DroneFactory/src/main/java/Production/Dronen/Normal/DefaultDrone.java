package Production.Dronen.Normal;
import Production.Dronen.Drone;

/**
 *
 * Eine ganz einfache einsiegs Drone, die nichts besonderes kann, ist aber billig.
 */
public class DefaultDrone extends Drone {

    public DefaultDrone() {
        super();
        id = 0;
        costs = 10;
        producitvity = 1;
        energy = 10;
        idelcosts = 1;
        producetime = 4;
    }

    /**
     *
     * @return: " <|> : Symbol einer Drone und uebrige arbeitskraft.
     */
    public String toString() {
        return "<|> : " + getEnergy();
    }
}
