package cz.hronza.rhrpoc.core.api.dto;

import java.time.OffsetDateTime;

public class OffsetDateTimeOutputDto {
    private OffsetDateTime offsetDateTime;

    public OffsetDateTimeOutputDto(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InputOutputDto{");
        sb.append(", offsetDateTime='").append(offsetDateTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
