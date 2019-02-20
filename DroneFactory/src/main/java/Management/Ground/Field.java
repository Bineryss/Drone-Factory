package Management.Ground;

import Management.BuildingManagement;
import Management.DroneManagement;
import Production.Dronen.Drone;
import Production.Factories.Building;
import Production.Factories.Energy.Solarpannels;
import Production.Factories.Produktion.DroneFactory;
import Production.Factories.Resources.Extractor;

import java.util.Scanner;

public abstract class Field {

    protected Building placedBuilding;

    public Field(Building placedBuilding) {
        this.placedBuilding = placedBuilding;

    }


    public void placeBuilding(int id) {

        //TODO: Durch eingaben ersetzten!
        int droneID = 0;
        int droneCount = 1;

        switch (id) {
            case 0:
                addBulding(new Solarpannels(), DroneManagement.getDrone(droneID), droneCount);
                break;
            case 1:
                addBulding(new DroneFactory(), DroneManagement.getDrone(droneID), droneCount);
                break;
            case 2:
                System.out.println("Labor kommt noch");
                break;
            case 3:
                addBulding(new Extractor(), DroneManagement.getDrone(droneID), droneCount);
                break;
            case 4:
                System.out.println("Vault kommt noch");
                break;
        }

    }

    public void addBulding(Building tmp, Drone drone, int count) {
        tmp.startConstruction(drone.getType(), count);
        BuildingManagement.addBuilding(tmp);
    }

    private void finalAction() {
        int wahl = -1;
        do {
            wahl = intWahl();
            action(wahl);

        } while (wahl != -1);
    }

    protected abstract void action(int wahl);

    public void print() {
        System.out.println("--------------------\nAvailable Resources: ");
        System.out.println("--------------------");
        if (placedBuilding == null) {
            System.out.println("Available Buildings:\n0 - Sollarpannel\n1 - Dronefactory\n2 - Lab\n3 - Extractor\n4 - Vault");
        } else {
            System.out.println(placedBuilding);
            System.out.println("-----------------");
            if (placedBuilding.inConstruction()) {
                System.out.println(fromatedOptions());
                finalAction();
            }
        }
    }

    private String fromatedOptions() {
        StringBuilder str = new StringBuilder(getOptions());
        str.append("(-1) - Exit");
        return str.toString();
    }

    protected abstract String getOptions();

    protected int intWahl() {
        Scanner sc = new Scanner(System.in);
        int ausgabe = 0;
        boolean fertig = false;
        while (!fertig) {
            try {
                System.out.print("\nAction: ");
                ausgabe = Integer.parseInt(sc.nextLine());
                return ausgabe;
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine der oben gennanten Zahlen angeben!");
            }
        }
        return ausgabe;
    }

    protected int intEnergy() {
        Scanner sc = new Scanner(System.in);
        int ausgabe = 0;
        boolean fertig = false;
        while (!fertig) {
            try {
                System.out.print("\nEnergy: ");
                ausgabe = Integer.parseInt(sc.nextLine());
                return ausgabe;
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine der oben gennanten Zahlen angeben!");
            }
        }
        return ausgabe;
    }

    protected int intResources() {
        Scanner sc = new Scanner(System.in);
        String eingabe = "0";
        boolean fertig = false;
        while (!fertig) {
            try {
                System.out.print("\nResources: ");
                eingabe = sc.nextLine();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine der oben gennanten Zahlen angeben!");
            }
        }
        return Integer.parseInt(eingabe);
    }

    public String getIcon() {
        return placedBuilding.getIcon();
    }


}
