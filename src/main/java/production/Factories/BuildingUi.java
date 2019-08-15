package production.Factories;

import ImportandEnums.BuildingTypes;
import management.ManagementSystems.BuildingManagement;

public abstract class BuildingUi<T extends BuildingDataEntity> {
    private static final String DIVIDERLINE = "------------------------";
    protected final String ICON;

    protected T information;
    protected BuildingTypes type;

    protected BuildingUi(BuildingTypes type, int id) {
        this.type = type;
        ICON = type.getIcon();
        loadInformation(id);
    }

    public void loadInformation(int id) {
        this.information = (T) BuildingManagement
                .getBuilding(type, id).getInformation().getDataEntity();
    }

    public String printIcon() {
        String out = "[" + ICON + "|";
        if (information.inConstruction()) {
            out += constructionStatus();
        } else {
            out += drawResoucreSpace();
            out += drawInformationSpace();
        }
        out += "]";
        return out;
    }

    public String openWindow() {
        StringBuilder out = new StringBuilder();
        out.append(DIVIDERLINE);
        out.append(ICON);
        out.append(DIVIDERLINE);
        out.append(String.format("%n%n"));
        out.append(drawWindow());
        out.append(DIVIDERLINE);
        out.append("----");
        out.append(DIVIDERLINE);
        return out.toString();
    }

    protected abstract String drawResoucreSpace();

    protected abstract String drawInformationSpace();

    protected abstract String drawWindow();

    protected String printResource() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%3dE|", information.getEnergy().availableEnergy()));
        if (information.getStorage() == null) {
            str.append("____R");
        } else {
            str.append(String.format("%3dR", information.getStorage().inStorage()));
        }
        return str.toString();
    }

    protected String formatEnergy() {
        StringBuilder out = new StringBuilder();
        out.append("Internal Energy   Storage: ");
        out.append(String.format("%3dE / %3dE", information.getEnergy().availableEnergy(),
                type.getMaxCapacityEnergy()));
        out.append(String.format("%n"));
        return out.toString();
    }

    protected String formatStorage() {
        StringBuilder out = new StringBuilder();

        out.append("Internal Resource Storage: ");
        if (information.getStorage() == null) {
            out.append("No connection!");
        } else {
            out.append(String.format("%3dR / %3dR", information.getStorage().inStorage(),
                    type.getMaxCapacity()));
        }
        out.append(String.format("%n"));
        return out.toString();
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
