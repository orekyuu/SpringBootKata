package net.orekyuu.springbootkata.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DiContainerTutorial {
    @Autowired
    StringGenerator generator;

    @Test
    public void StringGeneratorが取れる() {
        assertThat(generator).isNotNull();
    }

    @Autowired
    UserService userService;

    @Test
    public void UserServiceを作る() {
        assertThat(userService.userCount()).isEqualTo(0);
    }

    @Autowired
    ApplicationContext context;

    @Test
    public void ApplicationContextを使ってインスタンスを取り出す() {
        UserService userService = context.getBean(UserService.class);
        assertThat(userService.userCount()).isEqualTo(0);
    }


}
