package net.orekyuu.springbootkata.component;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DiContainerTutorial {

    @Nested
    @SpringBootTest
    class HelloServiceTest {
        @Autowired
        HelloService helloService;

        @Test
        void Helloサービスを呼び出す() {
            assertThat(helloService.hello()).isEqualTo("hello");
        }
    }

    @Nested
    @SpringBootTest
    class SumServiceTest {
        @Autowired
        SumService sumService;

        @Test
        void 足し算() {
            assertThat(sumService.sum(1, 2)).isEqualTo(3);
        }
    }
}
