package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.exceptions.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class ACrudService<R extends JpaRepository<T, ID>, T, ID> {

    protected R repository;

    public ACrudService(R repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public List<T> saveMany(Collection<T> entities) {
        return repository.saveAll(entities);
    }

    @SneakyThrows
    public T read(ID id) {

        Optional<T> optEntity = repository.findById(id);
        if(!optEntity.isPresent()) throw new EntityNotFoundException();

        return optEntity.get();
    }

    public List<T> reads() {
        return repository.findAll();
    }

    @SneakyThrows
    public void deleteById(ID id) {
        if(!repository.existsById(id)) throw new EntityNotFoundException();

        repository.deleteById(id);
    }
}
