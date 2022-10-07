package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.domain.CurvePoint;
import com.nnk.springboot.poseidon.services.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurvePointRestController extends ACrudRestController<CurvePointService, CurvePoint, Integer> {

    @Autowired
    public CurvePointRestController(CurvePointService crudService) {
        super(crudService);
    }

    @Override
    public ResponseEntity<List<CurvePoint>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<CurvePoint> getById(Integer integer) {
        return super.getById(integer);
    }

    @Override
    public ResponseEntity delete(Integer integer) {
        return super.delete(integer);
    }
}