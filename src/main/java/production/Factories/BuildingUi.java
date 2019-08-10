package production.Factories;

import ImportandEnums.BuildingTypes;
import management.ManagementSystems.BuildingManagement;

public abstract class BuildingUi<T extends BuildingDataEntity> {
    private static final String DIVIDERLINE = "------------------------";

    protected T information;

    public abstract String drawIcon();

    public void loadInformation(int id) {
        this.information = (T) BuildingManagement
                .getBuilding(getType(), id).getInformation().getDataEntity();
    }

    protected abstract BuildingTypes getType();


    public String openWindow() {
        StringBuilder out = new StringBuilder();
        out.append(DIVIDERLINE + getIcon() + DIVIDERLINE + String.format("%n%n"));
        out.append(drawWindow());
        out.append(DIVIDERLINE + "----" + DIVIDERLINE);
        return out.toString();
    }

    protected abstract String drawWindow();

    protected abstract String getIcon();

    protected String printResource() {
        StringBuilder str = new StringBuilder();
        try {
            str.append(String.format("%3dE|", information.getEnergy().availableEnergy()));
            if (information.getStorage() == null) {
                str.append("____R");
            } else {
                str.append(String.format("%3dR", information.getStorage()));
            }
            return str.toString();

        } catch (NullPointerException e) {
            return " 0 E | 0 R ";
        }
    }

    protected String constructionStatus() {
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
