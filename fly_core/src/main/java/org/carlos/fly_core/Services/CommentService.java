package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Comment.CommentCreationDTO;
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
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsByHotel(Integer hotel_id) {
        return commentRepository.findAllByHotel_Id(hotel_id);
    }

    public List<Comment> getCommentsByUserAndHotel(Integer user_id, Integer hotel_id) {
        return commentRepository.findAllByUser_IdAndHotel_Id(user_id, hotel_id);
    }

    public Comment createComment(CommentCreationDTO comment) {
        var User = userRepository.findById(comment.getUser_id());
        var Hotel = hotelRepository.findById(comment.getHotel_id());

        if (User.isEmpty() || Hotel.isEmpty()) {
            throw new RuntimeException("User or Hotel not found");
        }

        return commentRepository.save(Comment.builder()
                .comment(comment.getComment())
                .rating(comment.getRating())
                .user(User.get())
                .hotel(Hotel.get())
                .build());
    }

    public Comment updateComment(Integer id, CommentCreationDTO comment) {
        var Comment = commentRepository.findById(id);
        if (Comment.isEmpty()) {
            throw new RuntimeException("Comment not found");
        }

        var User = userRepository.findById(comment.getUser_id());
        var Hotel = hotelRepository.findById(comment.getHotel_id());

        if (User.isEmpty() || Hotel.isEmpty()) {
            throw new RuntimeException("User or Hotel not found");
        }

        Comment.get().setComment(comment.getComment());
        Comment.get().setRating(comment.getRating());
        Comment.get().setUser(User.get());
        Comment.get().setHotel(Hotel.get());

        return commentRepository.save(Comment.get());
    }
}
