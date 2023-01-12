package cz.hronza.rhrpoc.core.api.api;

import cz.hronza.rhrpoc.core.api.dto.OffsetDateTimeOutputDto;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.OffsetDateTime;

import static cz.hronza.rhrpoc.core.api.api.Constant.APPLICATION_JSON;

@RequestMapping("/offsetdatetime/v1")
public interface PocRestApiOffsetdatetime {

    @GetMapping(
            value = "/plus-days",
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
}
