package SpecificExceptions;

public class NotEnoughResourceException extends Exception {

    public NotEnoughResourceException() {
        super("So viel Resourcen hast du nicht!");
    }
}
