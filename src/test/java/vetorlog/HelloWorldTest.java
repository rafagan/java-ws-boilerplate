package vetorlog;

import org.junit.jupiter.api.*;

/**
 * IMPORTANTE: Verifique sempre se está importando classes e métodos de org.junit.jupiter, caso contrário
 * estará correndo o risco de estar usando JUNIT4, o qual não está integrado ao projeto
 */
class HelloWorldTest {
    @BeforeAll
    static void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @BeforeEach
    void before() {
        System.out.println("@Before");
    }

    @Test
    void test() {
        System.out.println("@Test");
    }

    @AfterEach
    void after() {
        System.out.println("@After");
    }

    @AfterAll
    static void afterClass() {
        System.out.println("@AfterClass");
    }
}
