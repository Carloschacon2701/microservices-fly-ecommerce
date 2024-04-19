package org.carlos.fly_core.PaymentRail.Controllers;

import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.PaymentRail.DTO.StripeChargeDTO;
import org.carlos.fly_core.PaymentRail.Services.StripeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class StripeController {

    private final StripeService stripeService;


    @PostMapping("/charge")
    public PaymentIntent charge(@RequestBody StripeChargeDTO stripeChargeDTO){
        return stripeService.charge(stripeChargeDTO);
    }


}
