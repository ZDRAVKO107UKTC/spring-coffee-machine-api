package com.school.coffeemachine.api.controllers;

import com.school.coffeemachine.api.dto.BrewRequest;
import com.school.coffeemachine.api.dto.BrewResponse;
import com.school.coffeemachine.service.BrewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class BrewController {
    private final BrewService brewService;

    public BrewController(BrewService brewService) {
        this.brewService = brewService;
    }

    @PostMapping("/brew")
    public BrewResponse brew(@Valid @RequestBody BrewRequest req) {
        BigDecimal changeEur = brewService.brew(req.getRecipeId(), req.getPaymentAmount(), req.getPaymentCurrency());
        return new BrewResponse("SUCCESS", "Coffee brewed successfully.", changeEur);
    }
}