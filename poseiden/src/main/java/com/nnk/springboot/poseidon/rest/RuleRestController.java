package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.domain.RuleName;
import com.nnk.springboot.poseidon.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/rules")
public class RuleRestController extends ACrudRestController<RuleService, RuleName, Integer> {

    @Autowired
    public RuleRestController(RuleService crudService) {
        super(crudService);
    }

    @Override
    @PostMapping("save")
    public ResponseEntity<RuleName> save(@NotNull RuleName model) {
        return super.save(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Collection<RuleName>> saveAll(@NotNull Collection<RuleName> models) {
        return super.saveAll(models);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RuleName>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<RuleName> getById(@PathVariable @NotNull Integer id) {
        return super.getById(id);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable @NotNull Integer id) {
        return super.delete(id);
    }
}