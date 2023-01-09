package cz.hronza.rhrpoc.core.common.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RhrPocNotSavedException extends RuntimeException {
    private final  List<KeyValue> parameters;

    public List<KeyValue> getParameters() {
        return parameters;
    }

    public RhrPocNotSavedException(String errorMessage, KeyValue... params) {
        super(String.format("%s,%s", errorMessage, Arrays.toString(params)));
        this.parameters = params != null ? Arrays.asList(params) : new ArrayList<>();
    }
}
