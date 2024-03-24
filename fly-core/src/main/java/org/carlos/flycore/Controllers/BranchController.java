package org.carlos.flycore.Controllers;

import lombok.RequiredArgsConstructor;
import org.carlos.flycore.DTO.Branch.BranchCreationDTO;
import org.carlos.flycore.Models.Branch;
import org.carlos.flycore.Services.BranchService;
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
    public Branch getBranchByHotel(Integer hotel_id) {
        return branchService.getBranchByHotel(hotel_id);
    }

    @PostMapping
    public Branch createBranch(BranchCreationDTO branch) {
        return branchService.createBranch(branch);
    }
}
