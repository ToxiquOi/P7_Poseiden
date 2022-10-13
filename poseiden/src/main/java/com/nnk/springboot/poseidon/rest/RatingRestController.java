package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.domain.Rating;
import com.nnk.springboot.poseidon.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/ratings/")
public class RatingRestController extends ACrudRestController<RatingService, Rating, Integer> {

    @Autowired
    public RatingRestController(RatingService crudService) {
        super(crudService);
    }

    @Override
    @PostMapping("save")
    public ResponseEntity<Rating> save(@NotNull Rating model) {
        return super.save(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Collection<Rating>> saveAll(@NotNull Collection<Rating> models) {
        return super.saveAll(models);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Rating>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Rating> getById(@PathVariable @NotNull Integer id) {
        return super.getById(id);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable @NotNull Integer id) {
        return super.delete(id);
    }
}