package cz.hronza.rhrpoc.core.common.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorItemDto {
    @JsonProperty("correlationId")
    private String correlationId;

    @JsonProperty("severity")
    private SeverityEnum severity;

    @JsonProperty("time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private OffsetDateTime time;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("parameters")
    @Valid
    List<ErrorParameterDto> parameters = null;

    @JsonProperty("code")
    private String code;

    public ErrorItemDto() {
    }

    public ErrorItemDto addParametersItem (ErrorParameterDto errorParameterDto) {
        if (this.parameters == null) this.parameters = new ArrayList<>();
        this.parameters.add(errorParameterDto);
        return this;
    }

    public ErrorItemDto collerationId ( String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @ApiModelProperty ("")
    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public ErrorItemDto severity ( ErrorItemDto.SeverityEnum severity) {
        this.severity = severity;
        return this;
    }

    @ApiModelProperty (
            example = "ERROR",
            required = true,
            value = ""
    )
    public SeverityEnum getSeverity() {
        return severity;
    }

    public void setSeverity(SeverityEnum severity) {
        this.severity = severity;
    }

    public ErrorItemDto time ( OffsetDateTime time) {
        this.time = time;
        return this;
    }

    @ApiModelProperty (
            example = "2020-12-02T15:23:05+02:00",
            required = true,
            value = "Date and Time when error occured"
    )
    @NotNull
    @Valid
    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public ErrorItemDto uuid ( String uuid) {
        this.uuid = uuid;
        return this;
    }

    @ApiModelProperty (
            example = "001a4a1a-0943-1eda-a5bb-e2ef174da424",
            required = true,
            value = "Unique identifier of the error occurence"
    )
    @NotNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ErrorItemDto parameters ( List<ErrorParameterDto> parameters) {
        this.parameters = parameters;
        return this;
    }

    @ApiModelProperty ("")
    @Valid
    public List<ErrorParameterDto> getParameters() {
        return parameters;
    }

    public void setParameters(List<ErrorParameterDto> parameters) {
        this.parameters = parameters;
    }

    public ErrorItemDto code(String code) {
        this.code = code;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorItemDto)) return false;
        ErrorItemDto that = (ErrorItemDto) o;
        return getCorrelationId().equals(that.getCorrelationId()) && getSeverity() == that.getSeverity() && getTime().equals(that.getTime()) && getUuid().equals(that.getUuid()) && getParameters().equals(that.getParameters()) && getCode().equals(that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCorrelationId(), getSeverity(), getTime(), getUuid(), getParameters(), getCode());
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorItemDto{");
        sb.append(" class ErrorItemDto implements java.io.Serializable {private static final serialVersionUID = 1L}");
        sb.append("correlationId='").append(toIdentedString(correlationId)).append('\n');
        sb.append(", severity=").append(toIdentedString(severity)).append('\n');
        sb.append(", time=").append(toIdentedString(time)).append('\n');
        sb.append(", uuid='").append(toIdentedString(uuid)).append('\n');
        sb.append(", parameters=").append(toIdentedString(parameters)).append('\n');
        sb.append(", code='").append(toIdentedString(code)).append('\n');
        sb.append('}');
        return sb.toString();
    }

    @ApiModelProperty (
            example = "ID_NOT_FOUND",
            required = true,
            value = "Code of the error, expression the type of the error"
    )
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public enum SeverityEnum {

        ERROR("ERROR"),
        WARNING("WARNING"),
        INFO("INFO");

        private final String value;

        SeverityEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        // k čemu to je ?

        @JsonCreator
        public static ErrorItemDto.SeverityEnum fromValue(String value) {
            ErrorItemDto.SeverityEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                ErrorItemDto.SeverityEnum b = var1[var3];
                if (b.value.equals(value)) return b;
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

    }
    private String toIdentedString(Object o) {
        return (o == null ) ? null : o.toString().replace("\n", "\n    ");
    }
}
