package Management.ManagementSystems;

import ImportandEnums.BuildingTypes;
import Production.Factories.Building;
import SpecificExceptions.DroneNotEnoughEnergyException;
import SpecificExceptions.NotEnoughEnergyException;
import SpecificExceptions.NotEnoughResourceException;

import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * Speichert alle Fabriken, Labore, Energie dings in einer Liste.
 * Speichert alle Dronen Typen in einern Liste.
 */
public class BuildingManagement {
    private static class BuildingKey {
        final BuildingTypes type;
        static int number = 0;

        BuildingKey(BuildingTypes type) {
            this.type = type;
            number++;
        }
    }

    private static final HashMap<BuildingKey, Building> buildings = new HashMap<>();

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
        StringBuilder out = new StringBuilder();
        for (BuildingKey key : buildings.keySet()) {
            if (key != null) {
                out.append(buildings.get(key)).append("; ");
            }
        }
        System.out.printf("%s%n", out.toString());
    }

    public static void update() {
        //TODO: try catch temporally until user interface gets implemented!
        buildings.forEach((key, building) -> {
            try {
                building.update();
            } catch (NotEnoughResourceException | NotEnoughEnergyException | DroneNotEnoughEnergyException e) {
                System.out.printf("%s%n",e.getMessage());
            }
        });

    }
}
