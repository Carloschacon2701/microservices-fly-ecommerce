package org.carlos.fly_core.DTO.Hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateHotelDTO {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "City cannot be null")
    private Integer city_id;

    @NotNull(message = "Branch cannot be null")
    private Integer branch_id;

    @NotBlank(message = "Address cannot be empty")
    @NotNull(message = "Address cannot be null")
    private String address;

    @NotBlank(message = "Phone cannot be empty")
    @NotNull(message = "Phone cannot be null")
    private String phone;

    @NotBlank(message = "Logo cannot be empty")
    @NotNull(message = "Logo cannot be null")
    private MultipartFile logo;

    @NotNull(message = "Stars cannot be null")
    private Integer stars;

    private MultipartFile image;

}
