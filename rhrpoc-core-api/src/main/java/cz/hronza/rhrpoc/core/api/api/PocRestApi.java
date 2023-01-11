package cz.hronza.rhrpoc.core.api.api;

import cz.hronza.rhrpoc.core.api.dto.NewStockId;
import cz.hronza.rhrpoc.core.api.dto.OffsetDateTimeOutputDto;
import cz.hronza.rhrpoc.core.api.dto.OutputDto;
import cz.hronza.rhrpoc.core.api.dto.ResultDto;
import cz.hronza.rhrpoc.core.api.dto.ResultRecDto;
import cz.hronza.rhrpoc.core.api.dto.StockDtoRec;
import cz.hronza.rhrpoc.core.api.dto.StockItemsMovementsListRecDto;
import cz.hronza.rhrpoc.core.common.enums.MultipleOperationsEnum;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import cz.hronza.rhrpoc.core.common.exception.ErrorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RequestMapping("/rhrpoc/v1")
public interface PocRestApi {
    String APPLICATION_JSON = "application/json";

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    default ResponseEntity<ResultRecDto> makeOperation(
            @ApiParam(value = "", required = true) @Valid @RequestParam Integer variableA,
            @ApiParam(value = "", required = true) @Valid @RequestParam Integer variableB,
            @ApiParam(value = "", required = true) @Valid @RequestParam OperationsEnum operationsEnum) {
        this.getRequest().ifPresent(request -> {
            Iterator var1 = MediaType.parseMediaTypes(request.getHeader("Accept")).iterator();
            while (var1.hasNext()) {
                MediaType mediaType = (MediaType) var1.next();
                if (mediaType.isCompatibleWith(MediaType.valueOf(APPLICATION_JSON))) {

                    ;
                    String someJson = "{addresKey}";
                    ApiUtil.setSomeResponse(request, APPLICATION_JSON, someJson);
                }
            }
        });
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(
            value = "Calculate the result.",
            nickname = "makeOperation",
            notes = "Service for to get the result.",
            response = ResultDto.class,
            tags = {"CALCULATE_THE_RESULT"}
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Calculation was successful.",
                    response = ResultDto.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Calculation was unsuccessful.",
                    response = ErrorDto.class
            )
    })
    @GetMapping(
            value = "/calculation",
            produces = APPLICATION_JSON
    )
    default ResponseEntity<ResultRecDto> _makeOperation(
            @Valid @RequestParam Integer variableA,
            @Valid @RequestParam Integer variableB,
            @Valid @RequestParam OperationsEnum operationsEnum
    ) {
        return this.makeOperation(variableA, variableB, operationsEnum);

    }

    @ApiOperation(
            value = "Calculate multiple operations the result.",
            nickname = "makeMultipleOperation",
            notes = "Service for to get multiple operations the result.",
            response = ResultDto.class,
            tags = {"CALCULATE_MULTIPLE_OPERATIONS_THE_RESULT"}
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Calculate multiple operations the result was successful.",
                    response = ResultDto.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Calculate multiple operations the result was un successful.",
                    response = ErrorDto.class
            )
    })
    @GetMapping(
            value = "/calculation/{multipleOperationsEnum}",
            produces = APPLICATION_JSON
    )
    default ResponseEntity<ResultRecDto> _makeMultipleOperation(
            @Valid @PathVariable MultipleOperationsEnum multipleOperationsEnum,
            @Valid @RequestParam List<Integer> numbers
    ) {
        return this.makeMultipleOperation(multipleOperationsEnum, numbers);

    }

    default ResponseEntity<ResultRecDto> makeMultipleOperation(
            @ApiParam(value = "", required = true) @Valid @PathVariable MultipleOperationsEnum multipleOperationsEnum,
            @ApiParam(value = "", required = true) @Valid @RequestParam List<Integer> numbers
    ) {
        this.getRequest().ifPresent(request -> {
            Iterator var1 = MediaType.parseMediaTypes(request.getHeader("Accept")).iterator();
            while (var1.hasNext()) {
                MediaType mediaType = (MediaType) var1.next();
                if (mediaType.isCompatibleWith(MediaType.valueOf(APPLICATION_JSON))) {

                    String someJson = "{addresKey}"; //TODO dodělat
                    ApiUtil.setSomeResponse(request, APPLICATION_JSON, someJson);
                }
            }
        });
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(
            value = "Adding salesman a sold products.",
            nickname = "addNewStock",
            notes = "Service for adding new stock.",
            response = StockDtoRec.class,
            tags = {"ADD_NEW_STOCK"}
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Add new stock.",
                    response = StockDtoRec.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Adding new stock was unsuccessful.",
                    response = ErrorDto.class
            )
    })
    @PostMapping(
            value = "/new-stock",
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

    @GetMapping(
            value = "/reverse-endpoint-from-easy-be",
            produces = APPLICATION_JSON
    )
    default ResponseEntity<OutputDto> _reverseEndpointFromEasyBe(
            @Valid @RequestParam String id,
            @Valid @RequestParam String name
    ) {
        return this.reverseEndpointFromEasyBe(id, name);

    }

    default ResponseEntity<OutputDto> reverseEndpointFromEasyBe(String id, String name) {
        return ResponseEntity.ok(new OutputDto("not yet implemented", "not yet implemented"));
    }

    @GetMapping(
            value = "/plus-days-for-offsetdatetime",
            produces = APPLICATION_JSON
    )
    default ResponseEntity<OffsetDateTimeOutputDto> _plusDaysForOffsetdatetime(
            @ApiParam("") @Valid @RequestParam(value = "offsetDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime offsetDateTime,
            @ApiParam("") @Valid @RequestParam(value = "days", required = false) Integer days
    ) {
        return this.plusDaysForOffsetdatetime(offsetDateTime, days);
    }

    default ResponseEntity<OffsetDateTimeOutputDto> plusDaysForOffsetdatetime(OffsetDateTime offsetDateTime, Integer days) {
        return ResponseEntity.ok(new OffsetDateTimeOutputDto(OffsetDateTime.MIN));
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
    @GetMapping(value = "/get-stock-items-and-movements", produces = APPLICATION_JSON)
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
                    Integer exampleId = 123456;
                    ApiUtil.setSomeResponse(request, APPLICATION_JSON, exampleId.toString());
                    break;
                }
            }
        });
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}

