package Management;

import List.*;
import Production.Factories.Building;

import java.util.InputMismatchException;

/**
 *
 * Speichert alle Fabriken, Labore, Energie dings in einer Liste.
 * Speichert alle Dronen Typen in einern Liste.
 */
public class BuildingManagement {
    //Anzahl aller ID`s
    private static final int IDCOUNT = 5;

    private static List<Building>[] BUILDINGS = new List[IDCOUNT];



    public static void start() {
        //Bebaeude Typen werden Ihrer ID zugeordnet
        for(int i = 0; i < BUILDINGS.length; i++) {
            BUILDINGS[i] = new List<Building>();
        }
    }

    public static void addBuilding(Building tmp) {
            BUILDINGS[tmp.getID()].append(tmp);
    }

    public static Building getBuilding(int[] id) {
        List<Building> search = BUILDINGS[id[0]];
        search.toFirst();
        while (search.hasAccess()) {
            if(search.getContent().getSID() == id[1]) {
                return search.getContent();
            }
            search.next();
        }
        throw new InputMismatchException("Die ID existiert nicht!");
    }

    public static String print() {
        String str = "";
        for (int i = 0; i < IDCOUNT; i++) {
            str += i + ": ";
            List<Building> tmp = BUILDINGS[i];
            tmp.toFirst();
            while (tmp.hasAccess()) {
                str += tmp.getContent().toString();
                tmp.next();
            }
            str += "\n";
        }
        return str;
    }

    public static void update() {
        for (int i = 0; i < IDCOUNT; i++) {
            List<Building> tmp = BUILDINGS[i];
            tmp.toFirst();
            while (tmp.hasAccess()) {
                tmp.getContent().update();
                tmp.next();
            }
        }
        print();
    }
}
