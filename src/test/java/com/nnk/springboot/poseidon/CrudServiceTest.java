package com.nnk.springboot.poseidon;

import com.nnk.springboot.poseidon.domain.User;
import com.nnk.springboot.poseidon.exceptions.EntityNotFoundException;
import com.nnk.springboot.poseidon.mocks.MockCrudService;
import com.nnk.springboot.poseidon.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CrudServiceTest {

    @Mock
    public UserRepository repository;
    public MockCrudService<UserRepository, User, Integer> service;

    @Before
    public void beforeEach() {
        service = new MockCrudService<>(repository);
    }

    @Test
    public void testSaveMethod() {
        when(repository.save(any(User.class))).thenAnswer(a -> {
            User u = ((User) a.getArguments()[0]);
            u.setId(666);
            return u;
        });

        Assert.assertEquals((Integer) 666, service.save(new User()).getId());
    }

    @Test
    public void testSaveAllMethod() {
        when(repository.saveAll(any(Collection.class))).thenAnswer(a -> {
            Collection<User> cu = ((Collection<User>) a.getArguments()[0]);
            AtomicReference<Integer> i = new AtomicReference<>(0);
            cu.forEach(u -> {u.setId(i.get()); i.set(i.get() + 1);});
            return cu;
        });

        AtomicReference<Integer> i = new AtomicReference<>(0);
        service.saveMany(Arrays.asList(new User(), new User())).forEach(user -> {
            Assert.assertEquals(i.get(), user.getId());
            i.set(i.get() + 1);
        });
    }

    @Test
    public void testReadMethodThrowEntityNotFound() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());
        Assert.assertThrows(EntityNotFoundException.class, () -> service.read(10));
    }

    @Test
    public void testUpdateMethod() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(new User()));
        when(repository.save(any(User.class))).thenAnswer(a -> a.getArguments()[0]);

        User data = new User();
        data.setId(10);
        data.setUsername("toto");
        data.setFullname("otot");

        User updated = service.update(10, data);
        Assert.assertEquals("toto", updated.getUsername());
        Assert.assertEquals("otot", updated.getFullname());
    }

    @Test
    public void testDeleteByIdThrowException() {
        when(repository.existsById(any(Integer.class))).thenReturn(false);
        Assert.assertThrows(EntityNotFoundException.class, () -> service.deleteById(10));
    }
}
