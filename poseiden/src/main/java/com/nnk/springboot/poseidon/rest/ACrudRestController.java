package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.exceptions.EntityNotFoundException;
import com.nnk.springboot.poseidon.services.ACrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public abstract class ACrudRestController <S extends ACrudService<?, T, ID>, T, ID> {

    protected S crudService;

    protected ACrudRestController(S crudService) {
        this.crudService = crudService;
    }

    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(crudService.reads());
    }

    public ResponseEntity<T> getById(ID id) {
        return ResponseEntity.ok(crudService.read(id));
    }

    public ResponseEntity delete(ID id) {
        crudService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<EntityNotFoundException> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }
}
