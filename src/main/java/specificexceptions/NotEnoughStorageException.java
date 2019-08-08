package specificexceptions;

public class NotEnoughStorageException extends Exception {

    public NotEnoughStorageException() {
        super("So viel kannst du nicht lagern!");
    }
}
