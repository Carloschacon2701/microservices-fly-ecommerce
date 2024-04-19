package org.carlos.fly_core.PaymentRail.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.PaymentRail.DTO.StripeChargeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StripeService {


    @Value("${application.stripe.api_key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }


     public PaymentIntent charge(StripeChargeDTO request){
         try{

             Map<String, Object> params = new HashMap<>();
             params.put("amount", (long) (request.getAmount() * 100));
             params.put("currency", "usd");
             params.put("description", "Charge for " + request.getUsername());
             params.put("payment_method", "pm_card_visa");

             Map<String, Object> automaticPaymentMethods = new HashMap<>();
             automaticPaymentMethods.put("enabled", true);
             automaticPaymentMethods.put("allow_redirects", "never");
             params.put("automatic_payment_methods", automaticPaymentMethods);

             PaymentIntent paymentIntent = PaymentIntent.create(params);

             paymentIntent.confirm();

                return paymentIntent;


         }catch (StripeException e){
             System.out.println(e.getMessage());
             throw new RuntimeException(e);

         }
     }
}
