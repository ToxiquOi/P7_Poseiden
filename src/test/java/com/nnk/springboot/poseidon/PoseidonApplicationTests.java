package com.nnk.springboot.poseidon;

import com.nnk.springboot.poseidon.mocks.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestSecurityConfig.class)
class PoseidonApplicationTests {

    @Test
    void contextLoads() {
    }

}
