package cz.hronza.rhrpoc.core.api.api;

import cz.hronza.rhrpoc.core.api.dto.ResultDto;
import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.Optional;

public interface PocRestApi {

    String APPLICATION_JSON = "application/json";

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @GetMapping(value = "/calculation")
    default ResponseEntity<ResultDto> _makeOperation(
            @Valid @RequestParam Integer variableA,
            @Valid @RequestParam Integer variableB,
            @Valid @RequestParam OperationsEnum operationsEnum
    ) {
        return this.makeOperation(variableA, variableB, operationsEnum);

    }

    default ResponseEntity<ResultDto> makeOperation(
            @ApiParam(value = "", required = true) @Valid @RequestParam Integer variableA,
            @ApiParam(value = "", required = true) @Valid @RequestParam Integer variableB,
            @ApiParam(value = "", required = true) @Valid @RequestParam OperationsEnum operationsEnum) {
        this.getRequest().ifPresent( request -> {
            Iterator var1 = MediaType.parseMediaTypes(request.getHeader("Accept")).iterator();
            while(var1.hasNext()) {
                MediaType mediaType = (MediaType) var1.next();
                if(mediaType.isCompatibleWith(MediaType.valueOf(APPLICATION_JSON))) {

                    String someJson = "{addresKey}"; //TODO dodělat
                    ApiUtil.setSomeResponse(request, APPLICATION_JSON, someJson);
                }
            }
        });
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}

