package cz.hronza.rhrpoc.core.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

public class ErrorParameterDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;


    public ErrorParameterDto() {
    }

    public ErrorParameterDto key (String key) {
        this.key = key;
        return this;
    }

    @ApiModelProperty(
            example = "TYP",
            required = true,
            value = "Key to additional value"
    )
    public String getKey() {
        return key;
    }

    public ErrorParameterDto setKey(String key) {
        this.key = key;
        return this;
    }


    public ErrorParameterDto value (String value) {
        this.value = value;
        return this;
    }

    @ApiModelProperty(
            example = "HISTORICAL",
            required = true,
            value = "One of additional values"
    )
    public String getValue() {
        return value;
    }

    public ErrorParameterDto setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorParameterDto)) return false;
        ErrorParameterDto that = (ErrorParameterDto) o;
        return getKey().equals(that.getKey()) && getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorParameterDto{");
        sb.append("key='").append(toIdentedString(key)).append('\n');
        sb.append(", value='").append(toIdentedString(value)).append('\n');
        sb.append('}');
        return sb.toString();
    }

    private String toIdentedString(Object o) {
        return (o == null ) ? null : o.toString().replace("\n", "\n    ");
    }
}
