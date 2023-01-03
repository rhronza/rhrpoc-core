package cz.hronza.rhrpoc.core.api.dto;

import java.util.List;

public record StockItemsMovementsListDtoRec(
        List<StockItemsMovementsDtoRec> stockItemsMovementsDtoRecList
) {
}
