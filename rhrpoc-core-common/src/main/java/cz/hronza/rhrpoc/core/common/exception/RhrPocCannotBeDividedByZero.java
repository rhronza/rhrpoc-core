package cz.hronza.rhrpoc.core.common.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * exception cannot by divided by zero
 */
public class RhrPocCannotBeDividedByZero extends RuntimeException {
    private final  List<String> params;

    public RhrPocCannotBeDividedByZero(String emsg, String... params) {
        super(emsg);
        this.params = new ArrayList<>(List.of(params));
    }

    public List<String> getParams() {
        return params;
    }
}
