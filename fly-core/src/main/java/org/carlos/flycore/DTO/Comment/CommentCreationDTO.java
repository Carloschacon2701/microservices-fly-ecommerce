package org.carlos.flycore.DTO.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentCreationDTO {

    @NotNull(message = "Comment cannot be null")
    @NotBlank(message = "Comment cannot be empty")
    private String comment;

    @NotNull(message = "Rating cannot be null")
    private Integer rating;

    @NotNull(message = "User id cannot be null")
    private Integer user_id;

    @NotNull(message = "Hotel id cannot be null")
    private Integer hotel_id;


}
