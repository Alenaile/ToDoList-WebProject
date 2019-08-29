package by.gsu.epamlab.exceptions;

public class DAOException extends Exception {
    private static final long serialVersionUID = 1L;

    public DAOException(String mess) {
        super(mess);
    }

    public DAOException(String mess, Throwable t) {
        super(mess, t);
    }

    public DAOException(Throwable t) {
        super(t);
    }

}
