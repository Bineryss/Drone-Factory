package SpecificExceptions;

public class NotEnoughEnergyException extends Exception {

    public NotEnoughEnergyException() {
        super("So viel Energie steht nicht zur verfuegung!");
    }
}
