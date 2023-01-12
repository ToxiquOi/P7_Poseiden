package com.nnk.springboot.poseidon.controllers.rest;

import com.nnk.springboot.poseidon.domain.Trade;
import com.nnk.springboot.poseidon.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/trades/")
public class TradeRestController extends ACrudRestController<TradeService, Trade, Integer> {

    @Autowired
    public TradeRestController(TradeService crudService) {
        super(crudService);
    }

    @Override
    @PostMapping("save")
    public ResponseEntity<Trade> save(@NotNull Trade model) {
        return super.save(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Collection<Trade>> saveAll(@NotNull Collection<Trade> models) {
        return super.saveAll(models);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Trade>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Trade> getById(@PathVariable @NotNull Integer id) {
        return super.getById(id);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable @NotNull Integer id) {
        return super.delete(id);
    }
}