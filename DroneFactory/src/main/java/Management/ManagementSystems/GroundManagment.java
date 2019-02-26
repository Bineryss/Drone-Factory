package Management.ManagementSystems;

import Production.Factories.Building;

public class GroundManagment {
    private static Building[][] FIELD;

    public static String print() {
        String str = "";
        for(int i = 0; i < FIELD.length; i++) {
            for(int j = 0; j < FIELD[i].length; j++) {
                str += "| " + FIELD[i][j].toString() + " ";
            }
            str += "|\n";
        }
        return str;
    }
}
