package com.nnk.springboot.poseidon.controllers.rest;

import com.nnk.springboot.poseidon.domain.BidList;
import com.nnk.springboot.poseidon.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/bidlists/")
public class BidListRestController extends ACrudRestController<BidListService, BidList, Integer> {

    @Autowired
    public BidListRestController(BidListService crudService) {
        super(crudService);
    }

    @Override
    @PostMapping("save")
    public ResponseEntity<BidList> save(@RequestBody @NotNull BidList model) {
        return super.save(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Collection<BidList>> saveAll(@RequestBody @NotNull Collection<BidList> models) {
        return super.saveAll(models);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BidList>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<BidList> getById(@PathVariable @NotNull Integer id) {
        return super.getById(id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable @NotNull Integer id) {
        return super.delete(id);
    }
}