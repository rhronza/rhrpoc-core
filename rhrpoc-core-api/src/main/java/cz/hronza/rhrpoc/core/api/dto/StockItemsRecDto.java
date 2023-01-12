package cz.hronza.rhrpoc.core.api.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record StockItemsRecDto(
        String itemTitle,
        String itemDescription,
        Long stockId,
        Long itemId,
        Long currenAmount,
        Long minimalAmount,
        LocalDate dateLastStocking,
        OffsetDateTime dateTimeLastIssue,
        List<MovementDtoRec> movementRecs
) {
}
