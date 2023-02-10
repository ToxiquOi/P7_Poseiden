package com.nnk.springboot.poseidon.controllers.rest;

import com.nnk.springboot.poseidon.domain.CurvePoint;
import com.nnk.springboot.poseidon.services.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/curvepoints/")
public class CurvePointRestController extends ACrudRestController<CurvePointService, CurvePoint, Integer> {

    @Autowired
    public CurvePointRestController(CurvePointService crudService) {
        super(crudService);
    }

    @Override
    @PostMapping("save")
    public ResponseEntity<CurvePoint> save(@NotNull @Valid CurvePoint model) {
        return super.save(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Collection<CurvePoint>> saveAll(@NotNull Collection<CurvePoint> models) {
        return super.saveAll(models);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CurvePoint>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<CurvePoint> getById(@PathVariable @NotNull Integer id) {
        return super.getById(id);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable @NotNull Integer id) {
        return super.delete(id);
    }
}