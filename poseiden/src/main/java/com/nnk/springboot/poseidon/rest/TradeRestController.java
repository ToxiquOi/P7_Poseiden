package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.domain.Trade;
import com.nnk.springboot.poseidon.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeRestController extends ACrudRestController<TradeService, Trade, Integer> {

    @Autowired
    public TradeRestController(TradeService crudService) {
        super(crudService);
    }

    @Override
    public ResponseEntity<List<Trade>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Trade> getById(Integer integer) {
        return super.getById(integer);
    }

    @Override
    public ResponseEntity delete(Integer integer) {
        return super.delete(integer);
    }
}