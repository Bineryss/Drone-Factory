package Management.ManagementSystems;

import ImportandEnums.BuildingTypes;
import Production.Factories.Building;

import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * Speichert alle Fabriken, Labore, Energie dings in einer Liste.
 * Speichert alle Dronen Typen in einern Liste.
 */
public class BuildingManagement {
    private static class BuildingKey {
        BuildingTypes type;
        static int number = 0;

        BuildingKey(BuildingTypes type) {
            this.type = type;
            number++;
        }
    }

    private static HashMap<BuildingKey, Building> buildings = new HashMap<>();

    public static void addBuilding(Building... tmp) {
        for (Building building : tmp) {
            buildings.put(new BuildingKey(building.getBuildingTypes()), building);
        }
    }

    public static Building getBuilding(BuildingTypes type, int id) {
        for (BuildingKey key : buildings.keySet()) {
            if (key != null && key.type == type && buildings.get(key).getId() == id) {
                return buildings.get(key);
            }
        }
        throw new InputMismatchException("No such id found!");
    }

    public static void print() {
        String out = "";
        for (BuildingKey key : buildings.keySet()) {
            if (key != null) {
                out += buildings.get(key) + "; ";
            }
        }
        System.out.printf("%s%n", out);
    }

    public static void update() {
        buildings.forEach((key, building) -> building.update());
    }
}
