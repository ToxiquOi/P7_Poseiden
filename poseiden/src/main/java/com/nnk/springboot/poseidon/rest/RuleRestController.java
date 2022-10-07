package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.domain.RuleName;
import com.nnk.springboot.poseidon.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RuleRestController extends ACrudRestController<RuleService, RuleName, Integer> {

    @Autowired
    public RuleRestController(RuleService crudService) {
        super(crudService);
    }

    @Override
    public ResponseEntity<List<RuleName>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<RuleName> getById(Integer integer) {
        return super.getById(integer);
    }

    @Override
    public ResponseEntity delete(Integer integer) {
        return super.delete(integer);
    }
}