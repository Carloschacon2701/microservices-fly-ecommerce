package org.carlos.fly_core.Services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Branch.BranchCreationDTO;
import org.carlos.fly_core.Models.Branch;
import org.carlos.fly_core.Repository.BranchRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public Branch getBranch(Integer id) {
        return branchRepository.findById(id).orElseThrow();
    }

    public Branch getBranchByHotel(Integer hotel_id) {
        return branchRepository.findBranchByHotel(hotel_id).orElseThrow();
    }

    public Branch createBranch(@Valid @RequestBody BranchCreationDTO branch) {
        var branchWithSameEmail = branchRepository.findByEmail(branch.getEmail());
        var branchWithSameName = branchRepository.findByName(branch.getName());

        if (branchWithSameEmail.isPresent()) {
            throw new IllegalArgumentException("Branch with email " + branch.getEmail() + " already exists");
        }

        if (branchWithSameName.isPresent()) {
            throw new IllegalArgumentException("Branch with name " + branch.getName() + " already exists");
        }

        return branchRepository.save(Branch.builder()
                .description(branch.getDescription())
                .email(branch.getEmail())
                .phone(branch.getPhone())
                .name(branch.getName())
                .logo(branch.getLogo())
                .build());
    }
}
