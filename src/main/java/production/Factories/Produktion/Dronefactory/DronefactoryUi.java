package production.Factories.Produktion.Dronefactory;

import ImportandEnums.BuildingTypes;
import production.Factories.BuildingUi;

public class DronefactoryUi extends BuildingUi<DronefactoryDataEntity> {
    private static final String icon = "[>%]";

    public String drawIcon() {
        String print = "[ " + icon + " |" + printResource() + "|(";
        if (information.getProducedElement() != null) {
            print += isWorkRemaining();
        }
        print += ")";
        if (information.getProd() != null) {
            print += information.getProd() + " ";
        }
        print += "]" + constructionStatus();
        return print;
    }

    @Override
    protected BuildingTypes getType() {
        return BuildingTypes.DRONEFACTORY;
    }

    protected String drawWindow() {
        StringBuilder out = new StringBuilder();
        out.append("Internal Energy   Storage: ");
        out.append(String.format("%3dE / %3dE", information.getEnergy().availableEnergy(),
                BuildingTypes.DRONEFACTORY.getMaxCapacityEnergy()));
        out.append(String.format("%n"));
        out.append("Internal Resource Storage: ");
        if (information.getStorage() == null) {
            out.append("No connection!");
        } else {
            out.append(String.format("%3dR / %3dR", information.getStorage().inStorage(),
                    BuildingTypes.DRONEFACTORY.getMaxCapacity()));
        }
        out.append(String.format("%n%n"));
        out.append("Current Drone in Production: ");
        if (information.getProducedElement() != null) {
            out.append(String.format("[%s]",information.getProducedElement().getType().getIcon()));
            out.append(String.format(" | Turns until finished: %d%n%n", information.getProductionStatus()));
        } else {
            out.append(String.format("[   ]%n%n"));
        }
        out.append("Available Drones for Production: " + information.convertProducibleDrones() + String.format("%n"));

        return out.toString();
    }

    @Override
    protected String getIcon() {
        return icon;
    }

    private String isWorkRemaining() {
        StringBuilder str = new StringBuilder(information.getProducedElement().getType().getIcon());
        if (information.isProducing()) {
            str.append(": ");
            for (int i = 0; i < information.getProductionStatus(); i++) {
                str.append("|");
            }
        }
        return str.toString();
    }
}
