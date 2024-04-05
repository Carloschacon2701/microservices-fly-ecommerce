package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Comment.CommentCreationDTO;
import org.carlos.fly_core.DTO.Comment.CommentDTO;
import org.carlos.fly_core.DTO.Comment.CommentDTOMapper;
import org.carlos.fly_core.DTO.Comment.CommentUpdateDTO;
import org.carlos.fly_core.Models.Comment;
import org.carlos.fly_core.Repository.CommentRepository;
import org.carlos.fly_core.Repository.HotelRepository;
import org.carlos.fly_core.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentDTOMapper commentDTOMapper;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    public CommentDTO convertToDTO(Comment comment) {
        return commentDTOMapper.apply(comment);
    }


    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsByHotel(Integer hotel_id) {
        return commentRepository.findAllByHotel_Id(hotel_id);
    }

    public List<CommentDTO> getCommentsByUserAndHotel(Integer user_id, Integer hotel_id) {
        return commentRepository.findAllByUser_IdAndHotel_Id(user_id, hotel_id)
                .stream().map(commentDTOMapper).toList();
    }

    public CommentDTO createComment(CommentCreationDTO comment) {
        var User = userRepository.findById(comment.getUser_id());
        var Hotel = hotelRepository.findById(comment.getHotel_id());

        if (User.isEmpty() || Hotel.isEmpty()) {
            throw new RuntimeException("User or Hotel not found");
        }

        Comment newComment =  commentRepository.save(Comment.builder()
                .comment(comment.getComment())
                .rating(comment.getRating())
                .user(User.get())
                .hotel(Hotel.get())
                .build());

        return convertToDTO(newComment);
    }

    public CommentDTO updateComment(Integer id, CommentUpdateDTO comment) {
        var Comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found")
        );

        if(comment.getComment() != null){
            Comment.setComment(comment.getComment());
        }

        if(comment.getRating() != null){
            Comment.setRating(comment.getRating());
        }

        return convertToDTO(commentRepository.save(Comment));
    }
}
