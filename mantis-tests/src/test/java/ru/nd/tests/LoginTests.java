package ru.nd.tests;

import org.testng.annotations.Test;
import ru.nd.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("adminstrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
