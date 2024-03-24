package org.carlos.fly_core.DTO.Reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationCreationDTO {

    @NotNull
    @Min(value= 1, message = "The room_id must be greater than 0")
    private Integer room_id;

    @NotNull
    private Integer user_id;

    @Future(message = "The checkIn date must be in the future")
    private Date checkIn;

    @Future(message = "The checkOut date must be in the future")
    private Date checkOut;

    @Min(value= 1, message = "The adults must be greater than 0")
    private Integer adults;

    @Min(value= 0, message = "The children must be greater than or equal to 0")
    private Integer children;

    @Min(value= 1, message = "The days must be greater than 0")
    private Integer days;

}
