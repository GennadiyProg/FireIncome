package com.example.fireincome.service;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.User;

import java.util.List;

public interface BranchService {
    Branch createBranch(Branch branch, String directorUsername);

    void deleteBranch(String kpp, String directorUsername);

    Branch getBranch(String kpp);

    Branch attachSeller(String kpp, User seller);

    void detachSeller(String kpp, User seller);
}
