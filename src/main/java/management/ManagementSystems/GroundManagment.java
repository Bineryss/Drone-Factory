package management.ManagementSystems;

import production.Factories.Building;

public class GroundManagment {
    private static Building[][] FIELD;

    public static String print() {
        StringBuilder str = new StringBuilder();
        for (Building[] buildings : FIELD) {
            for (int j = 0; j < buildings.length; j++) {
                str.append("| ").append(buildings[j].toString()).append(" ");
            }
            str.append("|\n");
        }
        return str.toString();
    }
}
