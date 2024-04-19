package org.carlos.fly_core.PaymentRail.DTO;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class StripeChargeDTO {
    private String username;
    private Double amount;
    private String message;

}
