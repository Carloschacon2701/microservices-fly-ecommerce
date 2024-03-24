package org.carlos.fly_core.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @GetMapping("/hotel/{hotel_id}")
    public String getCommentsByHotel(@PathVariable  Integer hotel_id) {
        return "Comments by hotel";
    }

    @GetMapping("/hotel/{hotel_id}/user/{user_id}")
    public String getCommentsByHotelAndUser(@PathVariable Integer hotel_id, @PathVariable Integer user_id) {
        return "Comments by hotel and user";
    }

    @PostMapping
    public String createComment() {
        return "Comment created";
    }

    @PutMapping
    public String updateComment() {
        return "Comment updated";
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Integer id) {
        return "Comment deleted";
    }
}
