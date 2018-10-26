package Production.Factories.Produktion;

import Management.DroneManagement;
import Production.Dronen.Drone;
import Production.Factories.Building;

/**
 *
 * Dronefactory - Produziert Dronen aller art.
 *
 * ID: 1
 */
public class DroneFactory extends Building {
    public final String ICON = "|>%|";
    private static int cc = -1;

    private int workStatus;
    private boolean isWorking;

    private static int[] produceableDronesId;
    private Drone producedElement;

    public DroneFactory() {
        super();
        cc++;
        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        id = 1;
        sid = cc;

        costs = 20;
        construction = 10;

        energyUse = 10;
        energy = 0;
        energyStorable = 200;

        resources = 0;
        resourcesStorable = 100;

        efficency = 2;

        efficency = 2;
        workStatus = 0;
        isWorking = false;

        produceableDronesId = new int[]{1};
    }

    /**
     *
     * Jeweils ein Cyclus beudeutet einer Runde
     * Die Fabrik benoetigt eine gewisse anzahl an Runden um die entscprechende Drone zu Produzieren
     * Die Fabrik verbraucht pro Runde jeweils einen gewissen betrag an Energie
     * Ist keine Energie mehr vorhanden, wird nicht weitergearbeitet
     *
     */
    public void update() {
        if(isWorking && hasEnergy() && isReady()) {
            useEnergy();
            workStatus -= 1 * efficency;
            finishDrone();
        }
    }



    /**
     * Started die Produktion einer Drone und verbraucht schonmal die benoetigten Resourcen
     * @param drone: Typ der Drone
     */
    public void startProduction(Drone drone) {
        if(canBeBuild(drone)) {
            if (!isWorking && isReady()) {
                if (hasResources(drone)) {
                    isWorking = true;
                    producedElement = drone;
                    workStatus += drone.getProducetime();
                    useResources(drone);
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
            }
        }
    }

    private boolean canBeBuild(Drone tmp) {
        for(int i = 0; i < produceableDronesId.length; i++) {
            if(tmp.getID() == produceableDronesId[i]) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "[ " + ICON + " , R: " + resources + ", E: " + energy + " (" + isWorkRemaining() + ")]" + constructionStatus();
    }

    private String isWorkRemaining() {
        String str = "";
        if(isWorking) {
            str += ": ";
            for(int i = 0; i < workStatus; i++) {
                str += "|";
            }
        }
        return str;
    }
}
