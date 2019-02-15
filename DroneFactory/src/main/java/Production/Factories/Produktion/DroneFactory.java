package Production.Factories.Produktion;

import Management.DroneManagement;
import Management.Resources.Energy;
import Management.Type;
import Management.Resources.Storage;
import Production.Dronen.Drone;
import Production.Factories.Building;

import java.util.LinkedList;
import java.util.List;

/**
 * Dronefactory - Produziert Dronen aller art.
 * <p>
 * ID: 1
 */
public class DroneFactory extends Building {
    private static int cc = -1;

    private int workStatus;
    private boolean isWorking;

    private static List<Type> produceableDronesId;
    private Drone producedElement;

    public DroneFactory() {
        super();
        cc++;
        sid = cc;

        type = Type.DRONEFACTORY;

        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        constructionCost = Type.DRONEFACTORY.getCosts();
        construction = Type.DRONEFACTORY.getConstructionTime();

        energy = new Energy(200, 10);

        storage = new Storage(300);
        storage.setMaxCapacity(Type.DRONEFACTORY.getMaxCapacity());

        efficiency = 2;

        efficiency = 2;
        workStatus = 0;
        isWorking = false;

        produceableDronesId = new LinkedList<>();
        produceableDronesId.add(Type.DEFAULTDRONE);
    }

    /**
     * Jeweils ein Zyclus beudeutet eine Runde
     * Die Fabrik benoetigt eine gewisse anzahl an Runden um die entscprechende Drone zu Produzieren
     * Die Fabrik verbraucht pro Runde jeweils einen gewissen betrag an Energie
     * Ist keine Energie mehr vorhanden, wird nicht weitergearbeitet
     */
    public void updateBuilding() {
        if (isWorking && hasEnergy() && isReady()) {
            useEnergy();
            workStatus -= 1 * efficiency;
            finishDrone();
        }
    }


    /**
     * Started die Produktion einer Drone und verbraucht schonmal die benoetigten Resourcen
     *
     * @param drone: Typ der Drone
     */
    public void startProduction(Drone drone) {
        if (canBeBuild(drone)) {
            if (!isWorking && isReady()) {
                if (hasResources(drone.getCosts())) {
                    isWorking = true;
                    producedElement = drone;
                    workStatus += drone.getProducetime();
                    useResources(drone.getCosts());
                } else {
                    throw new IllegalArgumentException("Du hast nicht genuegend Resourcen fuer diese Drone!");
                }
            } else {
                throw new IllegalArgumentException("Es wird bereits eine Drone Produziert!");
            }
        }
    }

    /**
     * Fertigt die Drone an
     */
    private void finishDrone() {
        if (isWorking) {
            if (workStatus == 0) {
                isWorking = false;
                DroneManagement.addDrone(producedElement);
                producedElement = null;
            }
        }
    }

    private boolean canBeBuild(Drone tmp) {
        if (produceableDronesId.contains(tmp.getType())) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("[ " + type.getIcon() + " || " + printResource() + " (");
        if (producedElement != null) {
            str.append(isWorkRemaining());
        }
        str.append(")]" + constructionStatus());
        return str.toString();
    }

    private String isWorkRemaining() {
        StringBuilder str = new StringBuilder(producedElement.getType().getIcon());
        if (isWorking) {
            str.append(": ");
            for (int i = 0; i < workStatus; i++) {
                str.append("|");
            }
        }
        return str.toString();
    }
}
