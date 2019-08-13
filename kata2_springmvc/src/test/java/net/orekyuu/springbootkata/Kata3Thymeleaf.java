package net.orekyuu.springbootkata;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Kata3Thymeleaf {

    @Value("${local.server.port}")
    int port;
    @Autowired
    InMemoryUserRepository repository;

    @BeforeEach
    void setup() {
        Configuration.timeout = 1000;
        Configuration.baseUrl = "http://localhost:" + port;
        Configuration.headless = true;

        repository.save(new User(1L, "Yamada"));
        repository.save(new User(2L, "Ito"));
        repository.save(new User(3L, "Sudo"));
    }

    @AfterClass
    static void tearDown() {
        Selenide.close();
    }

    @AfterEach
    void reset() {
        repository.removeAll();
    }

    @Test
    void ユーザー詳細のcssリンク() {
        open("/users/1");
        String href = $$("link").last().getAttribute("href");
        assertThat(href).matches("^http://localhost:\\d+/style/base.css$");
    }

    @Test
    void ユーザー詳細にユーザーの情報を出せている() {
        open("/users/1");
        String userId = $("#user-id").text();
        assertThat(userId).isEqualTo("1");

        String userName = $("#user-name").text();
        assertThat(userName).isEqualTo("Yamada");
    }

    @Test
    void ユーザー一覧が出せる() {
        open("/users");
        $$(".users > li").shouldHaveSize(3);

        String firstLink = $$(".users > li > a").first().attr("href");
        assertThat(firstLink).endsWith("/users/1");

        String lastLink = $$(".users > li > a").last().attr("href");
        assertThat(lastLink).endsWith("/users/3");
    }

    @Test
    void ユーザーの新規登録ができる() {
        open("/users/registration");

        // エラーなし
        $$(".error").shouldHaveSize(0);
        $("#name").val("new user");
        $("button[type=submit]").doubleClick();

        assertThat(repository.findAll()).hasSize(4);
        assertThat(repository.findAll().stream().map(User::getName)).contains("new user");

        assertThat(url()).matches("^http://localhost:\\d+/users$");
    }

    @Test
    void 名前が3文字未満() {
        open("/users/registration");

        // エラーなし
        $$(".error").shouldHaveSize(0);
        $("#name").val("aa");
        $("button[type=submit]").doubleClick();

        assertThat(repository.findAll()).hasSize(3);
        assertThat(url()).matches("^http://localhost:\\d+/users/registration$");

        $$(".error").shouldHaveSize(1);
        assertThat($("#name").val()).isEqualTo("aa");
    }
}
