package Production.Factories;

import Management.ResourceManagement;
import Production.Dronen.Drone;

import java.util.InputMismatchException;

/**
 * Interface fuer Gebaeude
 *
 * Speichert die Kosten, Bauzeit,
 *
 * ID: 0 = Energy, 1 = Production; 2 = Research; 3 = Resources; 4 = Storage
 */
public abstract class Building {
    //ID des Gebaeude Types
    protected int id;
    //ID des speziellen Gebaeude
    protected int sid;

    protected int costs;
    protected int construction;

    protected int energy;
    protected int energyUse;
    protected int energyStorable;

    protected int resources;
    protected int resourcesStorable;

    protected int efficency;

    public abstract void update();

    /**
     *
     * @param tmp: Drone wird uebergeben und Arbeitet mit ihrer Produktivitaet
     *           Meherer Dronen koennen gleichzeitig arbeiten
     *
     *           Wenn Drone keine arbeitskraft mehr, dann wird bau gestopt, neu Drone muss uebergen werden.
     */
    public void build(Drone[] tmp) {
        if(costs <= ResourceManagement.getResorcen() && tmp[0] != null) {
            for (int i = 0; i < tmp.length; i++) {
                construction = tmp[i].useForce(construction);
                if (tmp[i].useForce(construction) == 0) {
                    ResourceManagement.useResources(costs);
                    break;
                }
            }
        }
    }



    protected boolean hasEnergy() {
        return (energy - energyUse) >= 0;
    }

    protected void useEnergy() {
        if((energy - energyUse) >= 0) {
            energy -= energyUse;
        }else {
            throw new InputMismatchException("So viel Energie hast du nicht!");
        }
    }

    public void loadEnergy(int energy) {
        if((this.energy + energy) <= energyStorable && isReady()) {
            try {
                this.energy += ResourceManagement.useEnergy(energy);
            } catch (IllegalArgumentException e) {
                System.out.println("So viel Energie hast du nicht!");
            }
        }else {
            throw new IllegalArgumentException("So viel kannst du nicht lagern!");
        }
    }

    protected boolean hasResources(Drone drone) {
        return ((this.resources - (drone.getCosts()) >= 0));
    }

    protected void useResources(Drone drone) {
        if((resources -= drone.getCosts()) >= 0) {
            resources -= drone.getCosts();
        }else {
            throw new InputMismatchException("So viele Resourcen hast du nicht!");
        }
    }

    public void loadResources(int resources) {
        if((this.resources + resources) <= resourcesStorable && isReady()) {
            try {
                this.resources += ResourceManagement.useResources(resources);
            } catch (IllegalArgumentException e) {
                System.out.println("So viele Resourcen hast du nicht!");
            }
        }else {
            throw new IllegalArgumentException("So viel kannst du nicht lagern!");
        }
    }


    protected String constructionStatus() {
        if(construction > 0) {
            return ": X";
        }
        return "";
    }

    protected boolean isReady() {
        return construction == 0;
    }


    public int getSID() {
        return sid;
    }

    public int getID() {
        return id;
    }
}