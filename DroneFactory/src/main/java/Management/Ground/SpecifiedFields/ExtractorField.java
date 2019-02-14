package Management.Ground.SpecifiedFields;

import Management.Ground.Field;
import Production.Factories.Resources.Extractor;

public class ExtractorField extends Field {

    protected Extractor placedBuilding;

    public ExtractorField(Extractor building) {
        super(building);
        placedBuilding = building;
    }

    @Override
    protected void action(int wahl) {

    }

    @Override
    protected String getOptions() {
        return null;
    }
}
