package org.carlos.fly_core.DTO.Comment;

public record CommentDTO (
    Integer id,
    String comment,
     Integer rating,
     Integer user_id,
     Integer hotel_id

){}
