package com.djh.ci;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author David Hancock
 */
public class ApplicationTest {

    private Application application = new Application();

    @Test
    public void someTest() {

        String response = application.sayHello();
        Assert.assertEquals(response, "Hello!");
    }

}
