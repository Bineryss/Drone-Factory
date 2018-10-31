package Production.Factories.Resources;

import Management.DroneManagement;
import Management.Resources.Energy;
import Management.Resources.ResourceCosts;
import Management.Resources.ResourceManagement;
import Production.Dronen.Drone;
import Production.Factories.Building;

import java.util.Arrays;


/**
 * Extractor - Produziert Resourcen, wenn genug Energie vorhanden ist.
 * <p>
 * ID: 3
 */
public class Extractor extends Building {
    public final String ICON = "||-O";
    private static int cc = -1;

    private final ExtractorTyp typ;
    private int[] producableResources;


    private Drone transportDrone;

    public Extractor(ExtractorTyp typ) {
        super();
        cc++;
        id = 3;
        sid = cc;

        this.typ = typ;
        //Kosten Multuiplikatoren -> variable, damit Uprgades das senken koenne?
        switch (typ) {
            case CARBON:
                constructionCost = ResourceManagement.generateResourceArray(ResourceCosts.CARBONEXTRACTORCOSTS);
                construction = 5;

                energy = new Energy(100, 10);
                efficency = 2;

                resources = ResourceManagement.generateResourceArray("");
                resourcesStorable = ResourceManagement.generateResourceArray(ResourceCosts.CARBONEXTRACTORSTORABLE);
                producableResources = ResourceManagement.generateResourceArray(ResourceCosts.CARBONEXTRACTORRESOURCES);
                break;

            case GRAPHEN:
                constructionCost = ResourceManagement.generateResourceArray(ResourceCosts.GRAPHENEXTRACTORCOSTS);
                construction = 10;

                energy = new Energy(200,20);
                efficency = 2;

                resources = ResourceManagement.generateResourceArray("");
                resourcesStorable = ResourceManagement.generateResourceArray(ResourceCosts.GRAPHENEXTRACTORRESOURCES);
                producableResources = ResourceManagement.generateResourceArray(ResourceCosts.GRAPHENEXTRACTORSTORABLE);
                break;

            case COBALT:
                constructionCost = ResourceManagement.generateResourceArray(ResourceCosts.COBALTEXTRACTORCOSTS);
                construction = 20;

                energy = new Energy(500,40);
                efficency = 1;

                resources = ResourceManagement.generateResourceArray("");
                resourcesStorable = ResourceManagement.generateResourceArray(ResourceCosts.COBALTEXTRACTORSTORABLE);
                producableResources = ResourceManagement.generateResourceArray(ResourceCosts.COBALTEXTRACTORRESOURCES);
                break;
        }
        transportDrone = null;
    }

    public void update() {
        if (isReady()) {
            produceResources();
            if (transportDrone != null && Arrays.equals(resources, resourcesStorable)) {
                storeResources();
            }
        }
    }

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

    private void removeResources() {
        for (int i = 0; i < resources.length; i++) {
            resources[i] = 0;
        }
    }

    /**
     * Zieht eine Drone aus dem Dronemanagement
     *
     * @param id: Typ der Drone
     */
    public void addDrone(int id) {
        if (transportDrone == null) {
            transportDrone = DroneManagement.getDrone(id);
            DroneManagement.removeDrone(transportDrone);
        }
    }

    //dass soll sich später noch mit dronen aendern, dass die die resourcen abholen!
    private void produceResources() {
        if (!isFull()) {
            useEnergy();
            extractResource();
        }
    }

    private void extractResource() {
        for (int i = 0; i < resources.length; i++) {
            if (producableResources[i] != 0) {
                resources[i] += (producableResources[i] * efficency);
                if (resources[i] > resourcesStorable[i]) {
                    resources[i] = resourcesStorable[i];
                }
            }
        }
    }

    private boolean isFull() {
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] == resourcesStorable[i] && resourcesStorable[i] != 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * @return: Fertige Ausgabe
     */
    public String toString() {
        return "[ " + ICON + " |" + printResource() + " (" + printDrone() + ") ]" + constructionStatus();
    }

    private String printDrone() {
        if (transportDrone != null) {
            return transportDrone.toString();
        } else {
            return "";
        }
    }

}
