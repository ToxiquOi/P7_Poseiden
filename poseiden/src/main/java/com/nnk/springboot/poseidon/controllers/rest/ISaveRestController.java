package com.nnk.springboot.poseidon.controllers.rest;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

interface ISaveRestController<T, MS> {
    ResponseEntity<T> save(MS model);
    ResponseEntity<Collection<T>> saveAll(Collection<MS> models);
}
