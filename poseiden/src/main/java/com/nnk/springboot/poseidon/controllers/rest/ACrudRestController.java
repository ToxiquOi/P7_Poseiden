package com.nnk.springboot.poseidon.controllers.rest;

import com.nnk.springboot.poseidon.services.ACrudService;
import org.springframework.http.ResponseEntity;

import java.util.Collection;


public abstract class ACrudRestController <S extends ACrudService<?, T, ID>, T, ID> extends ACrudCustomSaveRestController<S, T, ID, T> {

    protected ACrudRestController(S crudService) {
        super(crudService, null, null);
    }

    @Override
    public ResponseEntity<T> save(T model) {
        return ResponseEntity.ok(crudService.save(model));
    }

    @Override
    public ResponseEntity<Collection<T>> saveAll(Collection<T> models) {
        return ResponseEntity.ok(crudService.saveMany(models));
    }
}

