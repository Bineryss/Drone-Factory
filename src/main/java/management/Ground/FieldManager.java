//package management.Ground;
//
//import management.Ground.SpecifiedFields.DroneFactoryField;
//import management.Ground.SpecifiedFields.ExtractorField;
//import management.Ground.SpecifiedFields.SolarpannelField;
//import production.Factories.Building;
//import production.Factories.Energy.Solarpannels;
//import production.Factories.Produktion.Dronefactory.DroneFactory;
//import production.Factories.Resources.Extractor;
//
//public class FieldManager {
//
//    private static final int FIELDSSIZE = 5;
//
//    private static Field[][] ground = new Field[FIELDSSIZE][FIELDSSIZE];
//
//
//
//    public static void addBuilding(Building building, int x, int y) {
//        if (true) {
//            switch (building.getBuildingTypes()) {
//                case SOLARPANNEL:
//                    ground[x][y] = new SolarpannelField((Solarpannels) building);
//                    break;
//                case DRONEFACTORY:
//                    ground[x][y] = new DroneFactoryField((DroneFactory) building);
//                    break;
//                case LABORATORIUM:
//                    break;
//                case EXTRACTOR:
//                    ground[x][y] = new ExtractorField((Extractor) building);
//                    break;
//                case VAULT:
//                    break;
//            }
//        }
//    }
///*Checkt, on position im berreich liegt
//    private static void choosePosition(int x, int y) {
//        posX = x;
//        posY = y;
//        positionSet = true;
//    }*/
//
//
//    public static String print() {
//        StringBuilder output = new StringBuilder();
//
//        for (int i = 0; i < FIELDSSIZE; i++) {
//            for (int j = 0; j < FIELDSSIZE; j++) {
//                output.append("------");
//            }
//            output.append("\n");
//            for (int j = 0; j < FIELDSSIZE; j++) {
//                if (ground[i][j] == null) {
//                    output.append("|    |");
//                } else {
//                    output.append(String.format("|%s|", ground[i][j].getIcon()));
//                }
//            }
//            output.append("\n");
//        }
//        for (int j = 0; j < FIELDSSIZE; j++) {
//            output.append("------");
//        }
//
//        return output.toString();
//    }
//
//}
