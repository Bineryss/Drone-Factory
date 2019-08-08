package production.Factories.Produktion.Dronefactory;

import ImportandEnums.BuildingTypes;
import management.ManagementSystems.BuildingManagement;

import java.util.Optional;

public class DronefactoryUi {
    private static final String icon = "[>%]";
    public static final String DIVIDERLINE = "------------------------";

    private DroneFactoryInformationElement information;

    public void loadInformation(int id) {
        information = ((DroneFactory) BuildingManagement.getBuilding(BuildingTypes.DRONEFACTORY, id)).inform();
    }

    public String icon() {
        String print = "[ " + icon + " |" + printResource() + "| (";
        if (information.getProducedElement() != null) {
            print += isWorkRemaining();
        }
        print += ") ";
        if (information.getProd() != null) {
            print += information.getProd() + " ";
        }
        print += "]" + constructionStatus();
        return print;
    }

    public String openWindow() {
        StringBuilder out = new StringBuilder();
        out.append(DIVIDERLINE + icon + DIVIDERLINE + String.format("%n%n"));
        out.append("Internal Energy   Storage: ");
        out.append(String.format("%4d", information.getEnergy()));
        out.append(String.format("E%n"));
        out.append("Internal Resource Storage: ");
        out.append(String.format("%4d", information.getStorage()));
        out.append(String.format("R%n%n"));
        out.append("Current Drone in Production: ");
        if (information.getProducedElement() != null) {
            out.append(information.getProducedElement());
        } else {
            out.append("[   ]");
        }
        out.append(" | Status: " + information.getWorkStatus() + String.format("%n%n"));
        out.append("Available Drones for Production: " + information.getProducibleDrones() + String.format("%n"));
        out.append(DIVIDERLINE + "----" + DIVIDERLINE);

        return out.toString();
    }

    private String isWorkRemaining() {
        StringBuilder str = new StringBuilder(information.getProducedElement().getType().getIcon());
        if (information.isWorking()) {
            str.append(": ");
            for (int i = 0; i < information.getWorkStatus(); i++) {
                str.append("|");
            }
        }
        return str.toString();
    }

    private String printResource() {
        StringBuilder str = new StringBuilder();
        try {
            str.append(information.getEnergy());
            str.append("E|");
            str.append(information.getStorage() + "R");
            return str.toString();

        } catch (NullPointerException e) {
            return " Energy: 0 | Resources: 0 ";
        }
    }

    private String constructionStatus() {
        StringBuilder out = new StringBuilder();
        for (int i = information.getConstruction(); i > 0; i--) {
            if (i >= 5) {
                out.append("X");
                i -= 4;
            } else {
                out.append("|");
            }
        }
        return out.toString();
    }
}
