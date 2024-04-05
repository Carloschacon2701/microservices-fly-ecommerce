package org.carlos.fly_core.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Comment.CommentCreationDTO;
import org.carlos.fly_core.DTO.Comment.CommentDTO;
import org.carlos.fly_core.DTO.Comment.CommentUpdateDTO;
import org.carlos.fly_core.Models.Comment;
import org.carlos.fly_core.Services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/hotel/{hotel_id}")
    public ResponseEntity<List<Comment>> getCommentsByHotel(@PathVariable  Integer hotel_id) {
        return ResponseEntity.ok(commentService.getCommentsByHotel(hotel_id));
    }

    @GetMapping("/hotel/{hotel_id}/user/{user_id}")
    public ResponseEntity<List<CommentDTO>> getCommentsByHotelAndUser(@PathVariable Integer hotel_id, @PathVariable Integer user_id) {
        return ResponseEntity.ok(commentService.getCommentsByUserAndHotel(user_id, hotel_id));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody @Valid CommentCreationDTO comment
    ) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable Integer id,
            @RequestBody CommentUpdateDTO comment
    ) {
        return ResponseEntity.ok(commentService.updateComment(id, comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Deleted succesfully");
    }
}
