package ru.nd.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import static org.testng.Assert.fail;

public class ApplicationManager {
    private final Properties properties;
    protected WebDriver driver;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;

    private StringBuffer verificationErrors = new StringBuffer();
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();

        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "C:\\utils\\geckodriver\\geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver", "C:\\utils\\chromedriver\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                System.setProperty("webdriver.ie.driver", "C:\\utils\\iedriver\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            MutableCapabilities options = new MutableCapabilities();

            if (browser.equals(BrowserType.FIREFOX)) {
                //System.setProperty("webdriver.gecko.driver", "C:\\utils\\geckodriver\\geckodriver.exe");
                //capabilities = DesiredCapabilities.firefox();
                options = new FirefoxOptions();
                //capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win8.1")));
            } else if (browser.equals(BrowserType.CHROME)) {
                //System.setProperty("webdriver.chrome.driver", "C:\\utils\\chromedriver\\chromedriver.exe");
                //capabilities = DesiredCapabilities.chrome();
                options = new ChromeOptions();
            } else if (browser.equals(BrowserType.IE)) {
                //System.setProperty("webdriver.ie.driver", "C:\\utils\\iedriver\\IEDriverServer.exe");
                //capabilities = DesiredCapabilities.internetExplorer();
                options = new InternetExplorerOptions();
            } else if (browser.equals(BrowserType.EDGE)) {
                //System.setProperty("webdriver.ie.driver", "C:\\utils\\iedriver\\IEDriverServer.exe");
                //capabilities = DesiredCapabilities.internetExplorer();
                options = new EdgeOptions();
            }

            //capabilities.setBrowserName(browser);
            //capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
            //capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win8.1")));
            options.setCapability(CapabilityType.PLATFORM, Platform.fromString(System.getProperty("platform", "win8.1")));
            driver = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), options);//capabilities);
        }

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUri"));

        sessionHelper = new SessionHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        groupHelper = new GroupHelper(driver);
        contactHelper = new ContactHelper(driver);

        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
