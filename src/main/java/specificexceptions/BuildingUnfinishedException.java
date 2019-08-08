package specificexceptions;

public class BuildingUnfinishedException extends Exception {

    public BuildingUnfinishedException() {
        super("Das Gebäude ist noch nicht Fertig");
    }

    public BuildingUnfinishedException(String message) {
        super(message);
    }
}
