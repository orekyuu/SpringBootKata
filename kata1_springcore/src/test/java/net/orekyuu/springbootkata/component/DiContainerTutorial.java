package net.orekyuu.springbootkata.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DiContainerTutorial {
    final SumService sumService;
    final HelloService helloService;

    DiContainerTutorial(HelloService helloService, SumService sumService) {
        this.helloService = helloService;
        this.sumService = sumService;
    }

    @Test
    void 足し算する() {
        assertThat(sumService.sum(1, 2)).isEqualTo(0);
    }

    @Test
    void Helloサービスを呼び出す() {
        assertThat(helloService.hello()).isEqualTo("hello");
    }
}
