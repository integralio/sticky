package io.integral.sticky;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StickyApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void failingTest() {
        fail("This is my first failing test!");
    }
}
