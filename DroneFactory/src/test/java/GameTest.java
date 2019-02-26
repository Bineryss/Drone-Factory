
import ImportandEnums.DroneTypes;
import Management.ManagementSystems.*;
import Production.Dronen.Drone;
import Production.Factories.Building;
import Production.Factories.Energy.*;
import Production.Factories.Connector.*;
import Production.Factories.Produktion.*;
import Production.Factories.Resources.*;
import SpecificExceptions.*;

import java.util.Scanner;

public class GameTest {

    private static void addBulding(Building tmp, DroneTypes type, int count) {
        tmp.startConstruction(type, count);
        BuildingManagement.addBuilding(tmp);
    }

    private static void newDrones(DroneTypes type, int n) {
        for (int i = 0; i < n; i++) {
            Drone tmp = new Drone(type);
            DroneManagement.addDrone(tmp);
        }
    }

    public static void main(String[] args) {
        ResourceManagement.addEnergy(200);
        ResourceManagement.addResources(800);

        newDrones(DroneTypes.DEFAULTDRONE, 10);
        int zug = 0;
        while (true) {
            zug(zug++);
        }
    }

    private static void zug(int zug) {
        BuildingManagement.update();
        boolean run = true;
        do {
            System.out.println("-----------Zug: " + zug + "---------------");
            System.out.println(ResourceManagement.print());
            DroneManagement.print();
BuildingManagement.print();
            try {
                run = runEingabe();
            } catch (Exception e) {
                System.out.println("Nicht vorhanden!");
            }
        } while (run);
        zug++;
    }

    private static boolean runEingabe() {
        int eingabe;
//        try {
            switch (intEingabe()) {
                case 0:
                    addBulding(new Solarpannels(), DroneTypes.DEFAULTDRONE, 1);
                    break;
                case 1:
                    addBulding(new DroneFactory(), DroneTypes.DEFAULTDRONE, 1);
                    break;
                case 2:
                    System.out.println("Labor kommt noch");
                    break;
                case 3:
                    addBulding(new Extractor(), DroneTypes.DEFAULTDRONE, 1);
                    break;
                case 4:
                    System.out.println("Vault kommt noch");
                    break;
                case 5:
                    //Extractor aufladen
//                    eingabe = intEingabe();
//                    InternalStorage tmpS = (InternalStorage) ((Extractor) BuildingManagement.getBuilding(new int[]{3, eingabe})).getStorage();
//                    tmpS.addTransportDrone(DroneTypes.DEFAULTDRONE);
//                    break;
//                case 6:
//                    BuildingManagement.getBuilding(new int[]{intEingabe(), intEingabe()}).addMoreWorkers(DroneTypes.DEFAULTDRONE, intEingabe());
//                    break;
//                case 7:
//                    //Dronefactory Laden
//                    eingabe = intEingabe();
//
//                    InternalStorage tmp3 = (InternalStorage) BuildingManagement.getBuilding(new int[]{1, eingabe}).getStorage();
//                    tmp3.loadResources(100);
//                    break;
//                case 8:
//                    DroneFactory tmpD = ((DroneFactory) BuildingManagement.getBuilding(new int[]{1, intEingabe()}));
//                    tmpD.startProduction(DroneTypes.DEFAULTDRONE);
                    System.out.println("New Production!");
                    break;
                case 9:
                    return false;
            }
//        } catch (BuildingUnfinishedException e) {
//            System.out.println(e.getMessage());
//        }
        return true;
    }


    private static int intEingabe() {
        Scanner sc = new Scanner(System.in);
        int ausgabe;
        while (true) {
            try {
                System.out.print("Zahl: ");
                ausgabe = Integer.parseInt(sc.nextLine());
                return ausgabe;
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine ganze Zahl eingeben!");
            }
        }
    }
}
