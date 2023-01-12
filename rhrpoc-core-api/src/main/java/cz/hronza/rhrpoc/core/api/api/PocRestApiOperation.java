package cz.hronza.rhrpoc.core.api.api;

import cz.hronza.rhrpoc.core.api.dto.ResultDto;
import cz.hronza.rhrpoc.core.api.dto.ResultRecDto;
import cz.hronza.rhrpoc.core.common.enums.MultipleOperationsEnum;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import cz.hronza.rhrpoc.core.common.exception.ErrorDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static cz.hronza.rhrpoc.core.api.api.Constant.APPLICATION_JSON;


@CrossOrigin
@RequestMapping("/operation/v1")
public interface PocRestApiOperation {

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
            produces = Constant.APPLICATION_JSON
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
}


