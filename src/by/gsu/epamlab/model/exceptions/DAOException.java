package by.gsu.epamlab.model.exceptions;

public class DAOException extends Exception {

    public DAOException(String mess) {
        super(mess);
    }

    public DAOException(String mess, Throwable t) {
        super(mess, t);
    }

}
