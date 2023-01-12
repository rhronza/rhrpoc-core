package cz.hronza.rhrpoc.core.api.api;

import cz.hronza.rhrpoc.core.api.dto.ReverseStringsOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static cz.hronza.rhrpoc.core.api.api.Constant.APPLICATION_JSON;

@CrossOrigin
@RequestMapping("/reverse-strings-from-easy-be/v1")
public interface PocRestApiReverseEndpointFromEasyBe {

    @GetMapping(
            value = "strings",
            produces = APPLICATION_JSON
    )
    default ResponseEntity<ReverseStringsOutput> _reverseStringsFromEasyBe(
            @Valid @RequestParam String string01,
            @Valid @RequestParam String string02
    ) {
        return this.reverseStringsFromEasyBe(string01, string02);

    }

    default ResponseEntity<ReverseStringsOutput> reverseStringsFromEasyBe(String string01, String string02) {
        return ResponseEntity.ok(new ReverseStringsOutput("not yet implemented", "not yet implemented"));
    }
}
