package production.Factories.Produktion.Dronefactory;

import ImportandEnums.BuildingTypes;
import production.Factories.BuildingUi;

public class DronefactoryUi extends BuildingUi<DronefactoryDataEntity> {

    public DronefactoryUi(int id) {
        super(BuildingTypes.DRONEFACTORY, id);
    }

    public String drawResoucreSpace() {
        return printResource();
    }

    @Override
    protected String drawInformationSpace() {
        String print = "|(";
        if (information.getProducedElement() != null) {
            print += isWorkRemaining();
        }
        print += ")";
        if (information.getProd() != null) {
            print += information.getProd();
        }
        return print;
    }

    protected String drawWindow() {
        StringBuilder out = new StringBuilder();
        out.append(formatEnergy());
        out.append(formatStorage());
        out.append(String.format("%n"));
        out.append("Current Drone in Production: ");
        if (information.getProducedElement() != null) {
            out.append(String.format("[%s]", information.getProducedElement().getType().getIcon()));
            out.append(String.format(" | Turns until finished: %d", information.getProductionStatus()));
        } else {
            out.append(String.format("[   ]"));
        }
        out.append(String.format("%n%nAvailable Drones for Production: %s%n", information.convertProducibleDrones()));

        return out.toString();
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
