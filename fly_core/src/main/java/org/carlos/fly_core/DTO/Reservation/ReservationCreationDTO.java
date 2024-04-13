package org.carlos.fly_core.DTO.Reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationCreationDTO {

    @NotNull(message = "The room_id cannot be null")
    @Min(value= 1, message = "The room_id must be greater than 0")
    private Integer room_id;

    @NotNull(message = "The user_id cannot be null")
    private Integer user_id;

    @NotNull(message = "The hotel_id cannot be null")
    @Future(message = "The checkIn date must be in the future")
    private Date checkIn;

    @NotNull(message = "The hotel_id cannot be null")
    @Future(message = "The checkOut date must be in the future")
    private Date checkOut;

    @NotNull(message = "The adults cannot be null")
    @Min(value= 1, message = "The adults must be greater than 0")
    private Integer adults;

    @NotNull(message = "The children cannot be null")
    @Min(value= 0, message = "The children must be greater than or equal to 0")
    private Integer children;

    @NotNull(message = "The days cannot be null")
    @Min(value= 1, message = "The days must be greater than 0")
    private Integer days;

}
