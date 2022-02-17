package com.smoothstack.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.condition.JRE.*;
import static org.junit.jupiter.api.condition.OS.MAC;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoTest {

    private Demo demo;

    @BeforeEach
    void setUp() {
        demo = new Demo();
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Running a specific test.");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Finishing a specific test.");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Running a test.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finished the test.");
    }

    @DisplayName("A special test case \uD83D\uDE31")
    @Test
    @DisabledOnJre(JAVA_9)
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @EnabledIfEnvironmentVariable(named = "USER", matches = "user")
    @Order(2)
    public void testHello() {
        assertNotNull(demo);
        assert demo.hello() == "hello";
    }

    // JUnit automatically recognizes the following as a @Test method that is tagged with "custom".
    @CustomTest
    @EnabledOnOs(MAC)
    @EnabledOnJre({ JAVA_9, JAVA_10, JAVA_17 })
    @EnabledIf("customCondition")
    @Order(1)
    public void testWorld() {
        assumeTrue("user".equals(System.getenv("USER")));
        assertNotNull(demo);
        assert demo.world() == "world";
    }

    public boolean customCondition() {
        return true;
    }

    @Disabled("This feature is not supported.")
    public void testFeature() {
        assert false;
    }

    @AfterEach
    void tearDown() {
        demo = null;
    }
}