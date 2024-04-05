package org.carlos.fly_core.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Branch.BranchCreationDTO;
import org.carlos.fly_core.Models.Branch;
import org.carlos.fly_core.Services.BranchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping("/{id}")
    public Branch getBranch(@PathVariable Integer id) {
        return branchService.getBranch(id);
    }

    @GetMapping("/hotel/{hotel_id}")
    public Branch getBranchByHotel( @PathVariable Integer hotel_id) {
        return branchService.getBranchByHotel(hotel_id);
    }

    @PostMapping
    public Branch createBranch(@RequestBody @Valid BranchCreationDTO branch) {
        return branchService.createBranch(branch);
    }
}
