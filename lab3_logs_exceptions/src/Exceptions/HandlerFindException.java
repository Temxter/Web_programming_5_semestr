package Exceptions;

/**
 * The type Handler find exception.
 */
public class HandlerFindException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.print("Error to find. Reason: incorrect ArrayLists.");
        super.printStackTrace();
    }
}