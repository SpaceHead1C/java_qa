package ru.nd.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.nd.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = false)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_defaults_inc.php"), "config_defaults_inc.php", "config_defaults_inc.php.bac");
    }

    @AfterSuite(alwaysRun = false)
    public void tearDown() throws IOException {
        app.ftp().restore("config_defaults_inc.php.bac", "config_defaults_inc.php");
        app.stop();
    }


}
