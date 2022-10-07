package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.domain.Rating;
import com.nnk.springboot.poseidon.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingRestController extends ACrudRestController<RatingService, Rating, Integer> {

    @Autowired
    public RatingRestController(RatingService crudService) {
        super(crudService);
    }

    @Override
    public ResponseEntity<List<Rating>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Rating> getById(Integer integer) {
        return super.getById(integer);
    }

    @Override
    public ResponseEntity delete(Integer integer) {
        return super.delete(integer);
    }
}