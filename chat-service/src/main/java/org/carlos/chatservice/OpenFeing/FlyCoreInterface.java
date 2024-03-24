package org.carlos.chatservice.OpenFeing;

import org.carlos.chatservice.Dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("FLY-CORE")
public interface FlyCoreInterface {
    @GetMapping("api/user/{id}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable Integer id
    );
}
