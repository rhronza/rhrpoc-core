package cz.hronza.rhrpoc.core.api.api;

import cz.hronza.rhrpoc.core.api.dto.NewStockId;
import cz.hronza.rhrpoc.core.api.dto.StockDtoRec;
import cz.hronza.rhrpoc.core.api.dto.StockItemsMovementsListRecDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static cz.hronza.rhrpoc.core.api.api.Constant.APPLICATION_JSON;

@CrossOrigin
@RequestMapping("/stock/v1")
public interface PocRestApiStock {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @PostMapping(
            value = "",
            consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON
    )
    default ResponseEntity<NewStockId> _addNewStock(
            @ApiParam(value = "", required = true) @Valid @RequestBody StockDtoRec stockDtoRec) {
        return addNewStock(stockDtoRec);
    }

    default ResponseEntity<NewStockId> addNewStock(StockDtoRec stockDtoRec) {
        this.getRequest().ifPresent((request) -> {
            Iterator var1 = MediaType.parseMediaTypes(request.getHeader("Accept")).iterator();
            while (var1.hasNext()) {
                MediaType mediaType = (MediaType) var1.next();
                if (mediaType.isCompatibleWith(MediaType.valueOf(APPLICATION_JSON))) {
                    String exampleString = "null";
                    ApiUtil.setSomeResponse(request, APPLICATION_JSON, exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(
            value = "get stock items and movements",
            nickname = "getStockItemsAndMovements",
            notes = "get stock items  and movements",
            response = StockItemsMovementsListRecDto.class,
            tags = "RHR_POC_STOCK_ITEMS_AND_AND_MOVEMENTS"
    )
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "succesfull response message",
            response = StockItemsMovementsListRecDto.class
    )})
    @GetMapping(value = "/items-and-movements", produces = APPLICATION_JSON)
    default ResponseEntity<StockItemsMovementsListRecDto> _getStockItemsAndMovements(
            @Valid @RequestParam(value = "stockIds", required = true) List<Long> stockIds
    ) {
        return getStockItemsAndMovements(stockIds);
    }

    default ResponseEntity<StockItemsMovementsListRecDto> getStockItemsAndMovements(List<Long> stockIds) {
        this.getRequest().ifPresent((request) -> {
            Iterator var1 = MediaType.parseMediaTypes(request.getHeader("Accept")).iterator();
            while (var1.hasNext()) {
                MediaType mediaType = (MediaType) var1.next();
                if (mediaType.isCompatibleWith(MediaType.valueOf(APPLICATION_JSON))) {
                    int exampleId = 123456;
                    ApiUtil.setSomeResponse(request, APPLICATION_JSON, String.valueOf(exampleId));
                    break;
                }
            }
        });
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}
