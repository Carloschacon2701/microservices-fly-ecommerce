package org.carlos.fly_core.DTO.Comment;

import org.carlos.fly_core.Models.Comment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CommentDTOMapper implements Function<Comment, CommentDTO> {
    @Override
    public CommentDTO apply(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getComment(),
                comment.getRating(),
                comment.getUser().getId(),
                comment.getHotel().getId()
        );
    }
}