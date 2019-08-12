package net.orekyuu.springbootkata;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Kata3Thymeleaf {

    @Value("${local.server.port}")
    int port;

    @BeforeEach
    void setup() {
        Configuration.timeout = 1000;
        Configuration.baseUrl = "http://localhost:" + port;
        Configuration.headless = true;
    }

    @AfterClass
    static void tearDown() {
        Selenide.close();
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
}
