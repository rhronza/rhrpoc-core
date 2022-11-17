package cz.hronza.rhrpoc.core.common.exception;

/**
 * exception cannot by divided by zero
 */
public class RhrCannotBeDividedByZero extends RuntimeException {
    public RhrCannotBeDividedByZero(String emsg) {
        super(emsg);
    }
}
