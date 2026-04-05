package com.mystore.base;

import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public static Properties prop;
    public static Action action = new Action();
    public static ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<>();

    @BeforeSuite(groups = {"Smoke","Sanity","Regression"})
    public void loadConfig() {
        ExtentManager.setExtent();
        DOMConfigurator.configure("log4j.xml");

        try {
            prop = new Properties();
            System.out.println("Super Constructor invoked");
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\Configuration\\Config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        //Get Driver from thread Local map
        return webDriver.get();
    }

    public static void launchApp(String browserName) {
//        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver.set(new FirefoxDriver());
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            webDriver.set(new EdgeDriver());
        }

        //Maximize the screen
        getDriver().manage().window().maximize();
        //Delete all the cookies
        getDriver().manage().deleteAllCookies();
        //Implicit TimeOuts
        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait")))
        );
        getDriver().manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTimeOut")))
        );
        //Launching the URL
        getDriver().get(prop.getProperty("url"));

    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.endReport();
    }

}
