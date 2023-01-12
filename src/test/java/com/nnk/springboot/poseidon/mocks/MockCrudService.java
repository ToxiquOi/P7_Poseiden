package com.nnk.springboot.poseidon.mocks;

import com.nnk.springboot.poseidon.services.ACrudService;
import org.springframework.data.jpa.repository.JpaRepository;


public class MockCrudService<R extends JpaRepository<T, ID>, T, ID> extends ACrudService<R, T,ID> {

    public MockCrudService(R repository) {
        super(repository);
    }
}
