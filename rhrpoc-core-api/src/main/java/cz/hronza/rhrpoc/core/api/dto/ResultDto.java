package cz.hronza.rhrpoc.core.api.dto;

public class ResultDto {
    private String operation;
    private String result;


    public String getResult() {
        return result;
    }

    public ResultDto setResult(String result) {
        this.result = result;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public ResultDto setOperation(String operation) {
        this.operation = operation;
        return this;
    }
}
