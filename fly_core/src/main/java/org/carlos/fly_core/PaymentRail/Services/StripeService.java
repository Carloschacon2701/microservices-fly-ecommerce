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


@Service
@RequiredArgsConstructor
public class StripeService {


    @Value("${application.stripe.api_key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }


     public boolean charge(StripeChargeDTO request){
         try{
             PaymentIntentCreateParams params = PaymentIntentCreateParams
                     .builder()
                     .setAmount((long) (request.getAmount() * 100))
                     .setCurrency("usd")
                     .setDescription(request.getMessage())
                     .setPaymentMethod("pm_card_visa")
                     .setAutomaticPaymentMethods(
                             PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                     .setEnabled(true)
                                     .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER).build()
                     )
                     .build();

        PaymentIntent result = PaymentIntent.create(params).confirm();

        return result.getStatus().equals("succeeded");

         }catch (StripeException e){
             System.out.println(e.getMessage());
             throw new RuntimeException(e);

         }
     }
}
