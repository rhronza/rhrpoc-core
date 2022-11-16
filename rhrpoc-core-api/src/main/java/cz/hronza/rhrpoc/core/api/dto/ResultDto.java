package cz.hronza.rhrpoc.core.api.dto;

import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;

public class ResultDto {

    //TODO přidat OperationsEnum

    private OperationsEnum operationsEnum;
    private String result;


    public String getResult() {
        return result;
    }

    public ResultDto setResult(String result) {
        this.result = result;
        return this;
    }

       public OperationsEnum getOperationsEnum() {
        return operationsEnum;
    }

    public ResultDto setOperationsEnum(OperationsEnum operationsEnum) {
        this.operationsEnum = operationsEnum;
        return this;
    }
}
