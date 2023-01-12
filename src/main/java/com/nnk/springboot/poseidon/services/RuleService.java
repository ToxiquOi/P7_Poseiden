package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.domain.RuleName;
import com.nnk.springboot.poseidon.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService extends ACrudService<RuleNameRepository, RuleName, Integer> {

    @Autowired
    public RuleService(RuleNameRepository repo) {
        super(repo);
    }

}