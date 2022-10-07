package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.domain.BidList;
import com.nnk.springboot.poseidon.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BidListRestController extends ACrudRestController<BidListService, BidList, Integer> {

    @Autowired
    public BidListRestController(BidListService crudService) {
        super(crudService);
    }

    @Override
    public ResponseEntity<List<BidList>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<BidList> getById(Integer integer) {
        return super.getById(integer);
    }

    @Override
    public ResponseEntity delete(Integer integer) {
        return super.delete(integer);
    }
}