package cz.hronza.rhrpoc.core.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApiModel(description = "Error data transfer object ")
public class ErrorDto extends ErrorItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("errorItemDtos")
    @Valid
    private List<ErrorItemDto> errorItemDtos;

    public ErrorDto() {
    }

    public ErrorDto errorItemDtos(List<ErrorItemDto> errorItemDtos) {
        this.errorItemDtos = errorItemDtos;
        return this;
    }

    public ErrorDto addErrorItemDtos(ErrorItemDto errorItemDtos) {
        if (this.errorItemDtos == null) {
            this.errorItemDtos = new ArrayList<>();
        }
        this.errorItemDtos.add(errorItemDtos);
        return this;
    }

    @ApiModelProperty("")
    public List<ErrorItemDto> getErrorItemDtos() {
        return errorItemDtos;
    }

    public void setErrorItemDtos(List<ErrorItemDto> errorItemDtos) {
        this.errorItemDtos = errorItemDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorDto)) return false;
        if (!super.equals(o)) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return getErrorItemDtos().equals(errorDto.getErrorItemDtos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getErrorItemDtos());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorDto{");
        sb.append(" class ErrorDto implements java.io.Serializable {private static final serialVersionUID = 1L}");
        sb.append("   ").append(toIdentedString(super.toString())).append('\n');
        sb.append("   errorItemDtos=").append(toIdentedString(errorItemDtos)).append('\n');
        sb.append('}');
        return sb.toString();
    }

    private String toIdentedString(Object o) {
        return (o == null ) ? null : o.toString().replace("\n", "\n    ");
    }
}
