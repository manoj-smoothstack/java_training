package com.smoothstack.demo;
import org.junit.jupiter.api.*;
class DemoShortTest {
    private Demo demo;

    @BeforeEach
    void setUp() {
        demo = new Demo();
    }

    @Test
    public void testHello() {
        assert demo.hello() == "hello";
    }

    @Test
    public void testWorld() {
        assert demo.world() == "world";
    }

    @AfterEach
    void tearDown() {
        demo = null;
    }
}

