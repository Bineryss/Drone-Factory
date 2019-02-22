package Management;

import ImportandEnums.Type;
import Production.Factories.Building;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Speichert alle Fabriken, Labore, Energie dings in einer Liste.
 * Speichert alle Dronen Typen in einern Liste.
 */
public class BuildingManagement {
    private ArrayList<Building>[] buildings;


    private BuildingManagement(int buildingCount) {
        buildings = new ArrayList[buildingCount];
        //Gebaeude Typen werden Ihrer ID zugeordnet
        for (int i = 0; i < buildings.length; i++) {
            buildings[i] = new ArrayList<>();
        }
    }

    public void addBuilding(Building tmp) {
        buildings[typeToId(tmp.getType())].add(tmp);
    }

    public Building getBuilding(int[] id) {
        ArrayList<Building> search = buildings[id[0]];
        for (Building i : search) {
            if (i.getId() == id[1]) {
                return i;
            }
        }
        throw new InputMismatchException("Die ID existiert nicht!");
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < buildings.length; i++) {
            str += i + ": ";
            ArrayList<Building> tmp = buildings[i];
            for (Building building : tmp) {
                str += building.toString();
            }
            str += "\n";
        }
        return str;
    }

    public void update() {
        for (int i = 0; i < buildings.length; i++) {
            ArrayList<Building> tmp = buildings[i];
            for (Building building : tmp) {
                building.update();
            }
        }
        System.out.println(toString());
    }

    private int typeToId(Type type) {
        switch (type) {
            case SOLARPANNEL:
                return 0;
            case DRONEFACTORY:
                return 1;
            case LABORATORIUM:
                return 2;
            case EXTRACTOR:
                return 3;
            case VAULT:
                return 4;
            default:
                return -1;
        }
    }
}
