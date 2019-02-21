package Production.Factories.Research;

import ImportandEnums.Type;
import Production.Factories.Building;

/**
 * Kleines Labor
 * <p>
 * ID: 2
 */
public class Lab extends Building {
    private static int cc = -1;


    public Lab() {
        super(Type.LABORATORIUM);
        cc++;
        id = cc;
    }

    @Override
    public void updateBuilding() {

    }

    public String toString() {
        return "[ " + type.getIcon() + " ]" + constructionStatus();
    }

}
