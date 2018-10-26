package Production.Factories.Resources;

import Management.DroneManagement;
import Management.ResourceManagement;
import Production.Dronen.Drone;
import Production.Factories.Building;

/**
 *
 * Extractor - Produziert Resourcen, wenn genug Energie vorhanden ist.
 *
 * ID: 3
 */
public class Extractor extends Building {
    public final String ICON = "||-O";
    private static int cc = -1;

    private Drone transportDrone;

    public Extractor() {
        super();
        cc++;
        id = 3;
        sid = cc;

        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        costs = 5;
        construction = 5;

        energy = 0;
        energyStorable = 100;
        efficency = 40;

        resources = 0;
        resourcesStorable = 80;
        transportDrone = null;
    }

    public void update() {
        if(isReady()) {
            produceResources();
            if(transportDrone != null && resources == resourcesStorable) {
                storeResources();
            }
        }
    }

    public void storeResources() {
        if(!transportDrone.isDead()) {
            ResourceManagement.addRESOURCES(resources);
            resources = 0;
            transportDrone.useForce(1);
        }else {
            System.out.println("Keine Drone mehr!");
            transportDrone = null;
        }
    }

    public void addDrone(int id) {
        if(transportDrone == null) {
            transportDrone = DroneManagement.getDrone(id);
            DroneManagement.removeDrone(transportDrone);
        }
    }

    //Immoment produziert er ins hauptlager,
    //dass soll sich später noch mit dronen aendern, dass die die resourcen abholen!
    private void produceResources() {
        if(energy > 2 && (resources + efficency) <= resourcesStorable) {
            resources += efficency;
            energy -= 2;
        }else {
            System.out.println("Der Extractor hat nicht genug energie oder das Storage ist voll!");
        }
    }

    public String toString() {
        return "[ " + ICON + " , E: " + energy + " , R: " + resources + "(" + printDrone() + ") ]" + constructionStatus();
    }

    private String printDrone() {
        if(transportDrone != null) {
            return transportDrone.toString();
        } else {
            return "";
        }
    }
}
