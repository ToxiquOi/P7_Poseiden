package com.nnk.springboot.poseidon;

import com.nnk.springboot.poseidon.domain.User;
import com.nnk.springboot.poseidon.exceptions.EntityNotFoundException;
import com.nnk.springboot.poseidon.mocks.MockCrudService;
import com.nnk.springboot.poseidon.mocks.TestSecurityConfig;
import com.nnk.springboot.poseidon.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestSecurityConfig.class)
public class CrudServiceTest {

    @Mock
    public UserRepository repository;
    public MockCrudService<UserRepository, User, Integer> service;

    @BeforeEach
    public void beforeEach() {
        service = new MockCrudService<>(repository);
    }

    @Test
    void testSaveMethod() {
        when(repository.save(any(User.class))).thenAnswer(a -> {
            User u = ((User) a.getArguments()[0]);
            u.setId(666);
            return u;
        });

        assertEquals((Integer) 666, service.save(new User()).getId());
    }

    @Test
    void testSaveAllMethod() {
        when(repository.saveAll(any(Collection.class))).thenAnswer(a -> {
            Collection<User> cu = ((Collection<User>) a.getArguments()[0]);
            AtomicReference<Integer> i = new AtomicReference<>(0);
            cu.forEach(u -> {u.setId(i.get()); i.set(i.get() + 1);});
            return cu;
        });

        AtomicReference<Integer> i = new AtomicReference<>(0);
        service.saveMany(Arrays.asList(new User(), new User())).forEach(user -> {
            assertEquals(i.get(), user.getId());
            i.set(i.get() + 1);
        });
    }

    @Test
    void testReadMethodThrowEntityNotFound() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.read(10));
    }

    @Test
    void testUpdateMethod() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(new User()));
        when(repository.save(any(User.class))).thenAnswer(a -> a.getArguments()[0]);

        User data = new User();
        data.setId(10);
        data.setUsername("toto");
        data.setFullname("otot");

        User updated = service.update(10, data);
        assertEquals("toto", updated.getUsername());
        assertEquals("otot", updated.getFullname());
    }

    @Test
    void testDeleteByIdThrowException() {
        when(repository.existsById(any(Integer.class))).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> service.deleteById(10));
    }
}
