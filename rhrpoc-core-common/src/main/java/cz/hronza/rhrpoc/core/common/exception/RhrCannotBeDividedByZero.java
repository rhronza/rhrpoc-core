package cz.hronza.rhrpoc.core.common.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * exception cannot by divided by zero
 */
public class RhrCannotBeDividedByZero extends RuntimeException {
    final private List<String> params;

    public RhrCannotBeDividedByZero(String emsg, String... params) {
        super(emsg);
        this.params = new ArrayList<>(List.of(params));
    }

    public List<String> getParams() {
        return params;
    }
}
