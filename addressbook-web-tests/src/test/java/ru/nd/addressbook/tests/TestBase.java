package ru.nd.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.nd.addressbook.appmanager.ApplicationManager;
import ru.nd.addressbook.model.GroupData;
import ru.nd.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Listeners(MyTestListener.class)
public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
}
