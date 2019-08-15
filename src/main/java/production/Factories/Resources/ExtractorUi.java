package production.Factories.Resources;

import ImportandEnums.BuildingTypes;
import production.Factories.BuildingDataEntity;
import production.Factories.BuildingUi;
import production.Factories.Connector.InternalStorage;

public class ExtractorUi extends BuildingUi<BuildingDataEntity> {

    public ExtractorUi(int id) {
        super(BuildingTypes.EXTRACTOR, id);
    }

    @Override
    protected String drawResoucreSpace() {
        return printResource();
    }

    @Override
    protected String drawInformationSpace() {
        return String.format("|Rate: %s", information.getEfficiency());
    }

    @Override
    protected String drawWindow() {
        StringBuilder out = new StringBuilder();
        out.append(formatEnergy());
        out.append(formatStorage());

        out.append(String.format("%nResource production Rate: %d%n", information.getEfficiency()));
        if (information.getStorage() instanceof InternalStorage) {
            if (((InternalStorage) information.getStorage()).getTransportDrone() != null) {
                out.append(String.format("Transport Drone: %s%n", ((InternalStorage) information.getStorage())
                        .getTransportDrone().toString()));
            } else {
                out.append("Transport Drone: [  ]");
            }
        }
        return out.toString();
    }
}
