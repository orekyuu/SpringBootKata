package net.orekyuu.springbootkata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class Kata1Controller {

    @Autowired
    MockMvc mockMvc;

    @Test
    void helloWorldTest() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleController#helloWorld()
        // hint: https://www.baeldung.com/spring-new-requestmapping-shortcuts

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    void getParameterTest() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleController#sum(int, int)
        // hint: https://www.baeldung.com/spring-request-param

        mockMvc.perform(get("/calc").param("a", "10").param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("15"));
    }

    @Test
    void defaultParameterTest() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleController#sum(int, int)
        // hint: https://www.baeldung.com/spring-request-param

        mockMvc.perform(get("/calc").param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void multiValueParameterTest() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleController#sumAll(List<Integer>)
        // hint: https://www.baeldung.com/spring-request-param
        mockMvc.perform(get("/sumAll?value=1,2,3"))
                .andExpect(status().isOk())
                .andExpect(content().string("6"));

        mockMvc.perform(get("/sumAll?value=4&value=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("9"));
    }

    @Test
    void postTest() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleController#helloMessage(String)
        // hint: https://www.baeldung.com/spring-request-param
        mockMvc.perform(post("/hello").param("name", "orekyuu"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello orekyuu"));
    }

    @Test
    void pathVariable() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleController#helloMessageFromPath(String)
        // hint: https://www.baeldung.com/spring-request-param
        mockMvc.perform(post("/hello/orekyuu"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello orekyuu"));
    }

    @Test
    void exceptionHandler() throws Exception {
        // Please implementation.
        // net.orekyuu.springbootkata.presentation.controller.SampleController#invalidParameterErrorResponse()
        // hint: https://www.baeldung.com/exception-handling-for-rest-with-spring
        mockMvc.perform(post("/hello/hello"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("invalid parameter"));
    }
}
