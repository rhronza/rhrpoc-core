package cz.hronza.rhrpoc.core.api.dto;

import java.util.List;

public record StockDtoRec(
        String title,
        Integer area,
        List<Long> itemIds
) {
}
