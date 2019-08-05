package SpecificExceptions;

public class MissingTransportDrone extends Exception{

    public MissingTransportDrone() {
        super("Du hast kein Transportdrone zum transportieren der Resource!");
    }
}
