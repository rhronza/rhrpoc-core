package cz.hronza.rhrpoc.core.api.dto;

import java.util.List;

public record StockItemsMovementsListRecDto(
        List<StockItemsMovementsRecDto> stockItemsMovementsRecDtoList
) {
}
