package org.carlos.fly_core.DTO.Branch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchCreationDTO {

    @NotNull(message = "The Phone is required")
    @NotBlank(message = "The Phone is required")
    private String phone;

    @NotNull(message = "The Email is required")
    @NotBlank(message = "The Email is required")
    private String email;

    @NotNull(message = "The Description is required")
    @NotBlank(message = "The Description is required")
    private String description;

    private byte[] logo;

    @NotNull(message = "The name is required")
    @NotBlank(message = "The name is required")
    private String name;
}
