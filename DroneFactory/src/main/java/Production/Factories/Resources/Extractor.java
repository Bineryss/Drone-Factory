package Production.Factories.Resources;

import Management.DroneManagement;
import Management.Resources.ResourceCosts;
import Management.Resources.ResourceManagement;
import Production.Dronen.Drone;
import Production.Factories.Building;

/**
 * Extractor - Produziert Resourcen, wenn genug Energie vorhanden ist.
 * <p>
 * ID: 3
 */
public class Extractor extends Building {
    public final String ICON = "||-O";
    private static int cc = -1;

    private int[] producableResources;

    private Drone transportDrone;

    public Extractor(ExtractorTyp Typ) {
        super();
        cc++;
        id = 3;
        sid = cc;

        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        switch (Typ) {
            case CARBON:
                constructionCost = ResourceManagement.generateResourceArray(ResourceCosts.CARBONEXTRACTORCOSTS);
                construction = 5;

                energy = 0;
                energyStorable = 100;
                efficency = 40;

                resources = ResourceManagement.generateResourceArray("");
                resourcesStorable = ResourceManagement.generateResourceArray(ResourceCosts.CARBONEXTRACTORSTORABLE);
                producableResources = ResourceManagement.generateResourceArray(ResourceCosts.CARBONEXTRACTORRESOURCES);
                break;
            case GRAPHEN:

                break;
            case COBALT:

                break;
        }
        transportDrone = null;
    }

    public void update() {
        if (isReady()) {
            produceResources();
            if (transportDrone != null && resources == resourcesStorable) {
                storeResources();
            }
        }
    }

    //TODO: Code Aufraeumen, resources wieder an den Array anpassen
    public void storeResources() {
        if (!transportDrone.isDead()) {
            ResourceManagement.addResources(resources);
            removeResources();
            transportDrone.useEnergy(1);
        } else {
            System.out.println("Keine Drone mehr!");
            transportDrone = null;
        }
    }

    public void addDrone(int id) {
        if (transportDrone == null) {
            transportDrone = DroneManagement.getDrone(id);
            DroneManagement.removeDrone(transportDrone);
        }
    }

    //Immoment produziert er ins hauptlager,
    //dass soll sich später noch mit dronen aendern, dass die die resourcen abholen!
    private void produceResources() {
        if (energy > 2 && canExtract()) {
            extractResource();
            energy -= 2;
        } else {
            System.out.println("Der Extractor hat nicht genug energie oder das Lager ist voll!");
        }
    }

    private void removeResources() {
        for(int i = 0; i < resources.length; i++) {
            resources[i] = 0;
        }
    }

    private boolean canExtract() {
        for(int i = 0; i < resources.length; i++) {
            if((((resources[i] + efficency) > resourcesStorable[i]) ||
                    ((resources[i] + efficency) - resourcesStorable[i]) > efficency) && (resources[i] > 0)) {
                return false;
            }
        }
        return true;
    }

    private void extractResource() {
        for(int i = 0; i < resources.length; i++) {
            if(canExtract()) {
            resources[i] += efficency;
            }else if(resourcesStorable[i] < resources[i] + efficency){
                resources[i] = resourcesStorable[i];
            }
        }
    }

    public String toString() {
        return "[ " + ICON + " , E: " + energy + " , R: " + printResource() + " (" + printDrone() + ") ]" + constructionStatus();
    }

    private String printDrone() {
        if (transportDrone != null) {
            return transportDrone.toString();
        } else {
            return "";
        }
    }

    private String printResource() {
        for(int i = 0; i < resources.length; i++) {
            if(resources[i] != 0) {
                return resources[i] + "";
            }
        }
        return "0";
    }
}
