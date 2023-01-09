package cz.hronza.rhrpoc.core.api.dto;

import java.util.List;

public record StockItemsMovementsRecDto(
        Long id,
        String title,
        Integer area,
        List<StockItemsRecDto> stockList
) {
}
