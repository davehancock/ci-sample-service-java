package com.djh.ci;

import org.junit.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author David Hancock
 */
public class ApplicationTest {

    private Application application = new Application();

    @Test
    public void someTest() {

        String response = application.sayHello();
        assertEquals(response, "Hello!");
    }

}
