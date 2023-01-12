package cz.hronza.rhrpoc.core.api.dto;

import java.time.OffsetDateTime;

public record MovementDtoRec(
        Long stockId,
        Long itemId,
        Float amount,
        OffsetDateTime created
) {

}
