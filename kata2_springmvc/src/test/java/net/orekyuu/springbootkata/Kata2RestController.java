package net.orekyuu.springbootkata;

import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class Kata2RestController {
    @Autowired
    MockMvc mvc;
    @Autowired
    InMemoryUserRepository repository;

    @BeforeEach
    void setup() {
        repository.save(new User(1L, "Yamada"));
        repository.save(new User(2L, "Ito"));
        repository.save(new User(3L, "Sudo"));
    }

    @AfterEach
    void tearDown() {
        repository.removeAll();
    }

    @Test
    void findUser() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleRestController#show(long)
        // hint: https://www.baeldung.com/spring-request-response-body
        // hint: https://www.baeldung.com/spring-controller-vs-restcontroller
        mvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", equalTo(1)))
                .andExpect(jsonPath("name", equalTo("Yamada")));
    }

    @Test
    void findAll() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleRestController#list
        // hint: https://www.baeldung.com/spring-request-response-body
        // hint: https://www.baeldung.com/spring-controller-vs-restcontroller
        mvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)));
    }

    @Test
    void remove() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleRestController#delete(long)
        // hint: https://www.baeldung.com/spring-response-status
        mvc.perform(delete("/api/users/2"))
                .andExpect(status().isNoContent());

        assertThat(repository.findAll()).hasSize(2);
    }

    @Test
    void updateTest() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleRestController#update(long, String)
        mvc.perform(put("/api/users/2").param("name", "orekyuu"))
                .andExpect(status().isOk());

        assertThat(repository.findById(2L).map(User::getName)).contains("orekyuu");
    }

}
