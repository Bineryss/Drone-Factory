package Management;

import Production.Factories.Building;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Speichert alle Fabriken, Labore, Energie dings in einer Liste.
 * Speichert alle Dronen Typen in einern Liste.
 */
public class BuildingManagement {
    //Anzahl aller ID`s
    private static final int IDCOUNT = 5;

    private static ArrayList<Building>[] BUILDINGS = new ArrayList[IDCOUNT];


    public static void start() {
        //Bebaeude Typen werden Ihrer ID zugeordnet
        for (int i = 0; i < BUILDINGS.length; i++) {
            BUILDINGS[i] = new ArrayList<Building>();
        }
    }

    public static void addBuilding(Building tmp) {
        Type type = tmp.getType();
        switch (type) {
            case SOLARPANNEL:
                BUILDINGS[0].add(tmp);
                break;
            case DRONEFACTORY:
                BUILDINGS[1].add(tmp);
                break;
            case EXTRACTOR:
                BUILDINGS[3].add(tmp);
                break;
            case VAULT:
                BUILDINGS[4].add(tmp);
                break;
        }
    }

    public static Building getBuilding(int[] id) {
        ArrayList<Building> search = BUILDINGS[id[0]];
        for (Building i : search) {
            if (i.getSID() == id[1]) {
                return i;
            }
        }
        throw new InputMismatchException("Die ID existiert nicht!");
    }

    public static String print() {
        String str = "";
        for (int i = 0; i < IDCOUNT; i++) {
            str += i + ": ";
            ArrayList<Building> tmp = BUILDINGS[i];
            for (Building building : tmp) {
                str += building.toString();
            }
            str += "\n";
        }
        return str;
    }

    public static void update() {
        for (int i = 0; i < IDCOUNT; i++) {
            ArrayList<Building> tmp = BUILDINGS[i];
            for (Building building : tmp) {
                building.update();
            }
        }
        print();
    }
}
