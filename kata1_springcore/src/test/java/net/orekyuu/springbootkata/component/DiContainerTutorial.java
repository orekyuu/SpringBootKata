package net.orekyuu.springbootkata.component;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

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

    @Nested
    @SpringBootTest
    class CountUpServiceTest {
        @Autowired
        ApplicationContext context;

        /*
         * デフォルトではComponentはsingletonスコープで管理されます。
         * 都度インスタンスを生成したい場合は@Scopeアノテーションを使ってprototypeスコープで管理するようにします。
         *
         * Webアプリケーションの場合はrequestスコープやsessionスコープといったライフサイクルを指定することもできます。
         */
        @Test
        void countUp() {
            CountUpService countUpService1 = context.getBean(CountUpService.class);
            countUpService1.increment();

            assertThat(countUpService1.currentCount()).isEqualTo(1);

            CountUpService countUpService2 = context.getBean(CountUpService.class);
            countUpService2.increment();
            assertThat(countUpService2.currentCount()).isEqualTo(1);
        }
    }

    @Nested
    @SpringBootTest
    class ProxyTest {
        @Autowired
        SingletonComponent component;

        /*
         * Singletonなコンポーネント内でPrototypeなコンポーネントをAutowiredすると、PrototypeなコンポーネントがSingletonなコンポーネントに引きずられて
         * Prototypeなコンポーネントのインスタンスが入れ替わらない
         *
         * ScopeのproxyModeにTARGET_CLASSを指定すると、注入するクラスがProxyで包まれて都度新しいインスタンスをDIコンテナから取り出す挙動になる。
         */
        @Test
        void testGenerateRandomString() {
            String first = component.generateRandomString();
            String second = component.generateRandomString();
            assertThat(first).isNotEqualTo(second);
        }
    }
}
