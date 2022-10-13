package com.nnk.springboot.poseidon.rest;

import com.nnk.springboot.poseidon.exceptions.EntityNotFoundException;
import com.nnk.springboot.poseidon.mapper.ReflectMapper;
import com.nnk.springboot.poseidon.services.ACrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ACrudCustomSaveRestController <S extends ACrudService<?, T, ID>, T, ID, MS> implements ISaveRestController<T, MS> {

    private ReflectMapper<MS, T> saveMapper;

    protected S crudService;

    protected ACrudCustomSaveRestController(S crudService, Class<MS> classMS, Class<T> classT) {
        this.crudService = crudService;
        if(classMS != null && classT != null) {
            saveMapper = new ReflectMapper<>(classMS, classT);
        }
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

    public ResponseEntity<T> save(MS model) {
        return ResponseEntity.ok(crudService.save(saveMapper.mapToEntity(model)));
    }

    public ResponseEntity<Collection<T>> saveAll(Collection<MS> models) {
        return ResponseEntity.ok(crudService.saveMany(models.stream()
                .map(ms -> saveMapper.mapToEntity(ms))
                .collect(Collectors.toList())
            )
        );
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<EntityNotFoundException> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }
}