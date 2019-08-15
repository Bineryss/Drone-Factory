
import ImportandEnums.BuildingTypes;
import ImportandEnums.DroneTypes;
import ImportandEnums.ResourceConnectionsEnum;
import management.ManagementSystems.*;
import production.Dronen.Drone;
import production.Factories.Building;
import production.Factories.BuildingUi;
import production.Factories.Connector.*;
import production.Factories.Energy.Solarpannel.SolarpannelUi;
import production.Factories.Energy.Solarpannel.Solarpannels;
import production.Factories.Produktion.Dronefactory.DroneFactory;
import production.Factories.Produktion.Dronefactory.DronefactoryUi;
import production.Factories.Resources.*;
import specificexceptions.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameTest {
    private static List<BuildingUi> uis;

    private static void addBulding(Building tmp, DroneTypes type, int count) throws NotEnoughResourceException {
        tmp.startConstruction(type, count);
        BuildingManagement.addBuilding(tmp);
    }

    private static void newDrones(DroneTypes type, int n) {
        for (int i = 0; i < n; i++) {
            Drone tmp = new Drone(type);
            DroneManagement.addDrone(tmp);
        }
    }

    public static void main(String[] args) throws NotEnoughStorageException {
        ResourceManagement.addEnergy(200);
        ResourceManagement.addResources(800);
        uis = new ArrayList<>();
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
            System.out.println(DroneManagement.print());
            for (BuildingUi ui : uis) {
                System.out.println(ui.printIcon());
            }

            try {
                run = runEingabe();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Nicht vorhanden!");
            }
        } while (run);
        zug++;
    }

    private static boolean runEingabe() {
        int eingabe;
        try {
            switch (intEingabe()) {
                case 0:
                    addBulding(new Solarpannels(), DroneTypes.DEFAULTDRONE, 1);
                    uis.add(new SolarpannelUi(0));
                    break;
                case 1:
                    addBulding(new DroneFactory(), DroneTypes.DEFAULTDRONE, 1);
                    uis.add(new DronefactoryUi(0));
                    break;
                case 2:
                    System.out.println("Labor kommt noch");
                    break;
                case 3:
                    addBulding(new Extractor(), DroneTypes.DEFAULTDRONE, 1);
                    uis.add(new ExtractorUi(0));
                    break;
                case 4:
                    System.out.println("Vault kommt noch");
                    break;
                case 5:
//                    Extractor aufladen
                    eingabe = intEingabe();
                    ExtractorUi extUi = new ExtractorUi(eingabe);
                    System.out.println(extUi.openWindow());
                    BuildingManagement.getBuilding(BuildingTypes.EXTRACTOR, eingabe).connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE);
                    InternalStorage tmpS = (InternalStorage) (BuildingManagement.getBuilding(BuildingTypes.EXTRACTOR, eingabe)).getStorage();
                    tmpS.addTransportDrone(DroneTypes.DEFAULTDRONE);
                    System.out.println(extUi.openWindow());
                    break;
                case 51:
                    eingabe = intEingabe();
                    extUi = new ExtractorUi(eingabe);
                    System.out.println(extUi.openWindow());
                    System.out.print("Energy ammount: ");
                    BuildingManagement.getBuilding(BuildingTypes.EXTRACTOR, eingabe).getEnergy().loadEnergy(intEingabe());
                    System.out.println(extUi.openWindow());
                    break;
                case 6:
                    BuildingManagement.getBuilding(idToBuilding(intEingabe()), intEingabe()).addMoreWorkers(DroneTypes.DEFAULTDRONE, intEingabe());
                    break;
                case 7:
                    eingabe = intEingabe();
                    DronefactoryUi droUi = new DronefactoryUi(eingabe);
                    System.out.println(droUi.openWindow());
                    BuildingManagement.getBuilding(BuildingTypes.DRONEFACTORY, eingabe).connectStorage(ResourceConnectionsEnum.INTERNALSTORAGE);
                    tmpS = (InternalStorage) (BuildingManagement.getBuilding(BuildingTypes.DRONEFACTORY, eingabe)).getStorage();
                    tmpS.addTransportDrone(DroneTypes.DEFAULTDRONE);
                    System.out.println(droUi.openWindow());
                    break;
                case 71:
                    //                    Dronefactory Laden
                    eingabe = intEingabe();

                    InternalStorage tmp3 = (InternalStorage) BuildingManagement.getBuilding(BuildingTypes.DRONEFACTORY, eingabe).getStorage();
                    System.out.print("Resource ammount: ");
                    tmp3.loadResources(intEingabe());
                    break;
                case 8:
                    DroneFactory tmpD = ((DroneFactory) BuildingManagement.getBuilding(BuildingTypes.DRONEFACTORY, intEingabe()));
                    tmpD.startProduction(DroneTypes.DEFAULTDRONE);
                    System.out.println("New production!");
                    break;
                case 9:
                    return false;
            }
        } catch (BuildingUnfinishedException | NotEnoughResourceException | NotEnoughStorageException | DroneNotEnoughEnergyException | MissingTransportDrone e) {
            System.out.printf("%s%n", e.getMessage());
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.printf("Gebe ein Existierendes Gabauede an!%n");
        } catch (NotEnoughEnergyException e) {
            System.out.println("So viel Energy hast du nicht!");
        } catch (NoResourceConnection noResourceConnection) {
            System.out.println("Es gitb keine Resource connection!");
        }
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

    private static BuildingTypes idToBuilding(int type) {
        switch (type) {
            case 0:
                return BuildingTypes.SOLARPANNEL;
            case 1:
                return BuildingTypes.DRONEFACTORY;
            case 3:
                return BuildingTypes.EXTRACTOR;
            default:
                throw new InputMismatchException();
        }
    }
}
