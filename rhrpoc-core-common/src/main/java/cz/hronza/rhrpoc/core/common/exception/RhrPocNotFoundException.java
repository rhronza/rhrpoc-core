package cz.hronza.rhrpoc.core.common.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RhrPocNotFoundException extends RuntimeException {
    final List<KeyValue> parameters;

    public List<KeyValue> getParameters() {
        return parameters;
    }

    public RhrPocNotFoundException(String errorMessage, KeyValue... params) {
        super(errorMessage);
        this.parameters = params != null ? Arrays.asList(params) : new ArrayList<>();
    }
}
