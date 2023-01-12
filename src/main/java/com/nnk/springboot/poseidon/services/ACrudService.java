package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.exceptions.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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
        if (!optEntity.isPresent()) throw new EntityNotFoundException();

        return optEntity.get();
    }

    public List<T> reads() {
        return repository.findAll();
    }

    @SneakyThrows
    public T update(ID id, T data) {
        Set<Method> setMethods = Arrays.stream(data.getClass().getDeclaredMethods())
                .filter(method -> method.getName().startsWith("set")).collect(Collectors.toSet());
        Set<Method> getMethods = Arrays.stream(data.getClass().getDeclaredMethods())
                .filter(method -> method.getName().startsWith("get")).collect(Collectors.toSet());

        T entity = read(id);

        for (Method sm : setMethods) {
            Optional<Method> optGm = getMethods.stream()
                    .filter(m -> m.getName().contains(sm.getName().replace("set", "")))
                    .findFirst();
            if(optGm.isPresent()) {
                Method gm = optGm.get();
                Object getVal = gm.invoke(data);

                if (getVal != null)
                    sm.invoke(entity, getVal);
            }
        }

        return save(entity);
    }

    @SneakyThrows
    public void deleteById(ID id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException();

        repository.deleteById(id);
    }
}
