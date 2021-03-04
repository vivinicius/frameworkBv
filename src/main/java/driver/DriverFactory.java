package driver;


import config.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.lang.reflect.InvocationTargetException;

import static java.lang.Boolean.TRUE;

public class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createInstance() {
        Configuration configuration = ConfigurationManager.getConfiguration();
        WebDriver webdriver;

        webdriver = createInstance(configuration.browser());

        return webdriver;
    }

    public static WebDriver createInstance(String browser) {
        WebDriver driverInstance = null;

        try {
            DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());
            Class<?> driverClass = Class.forName(driverManagerType.browserClass());
            WebDriverManager.getInstance(driverManagerType).setup();
            Configuration configuration = ConfigurationManager.getConfiguration();

            if (TRUE.equals(configuration.headless())) { //Aqui vai a parte do headless
                driverInstance = defineHeadless(driverClass);
            } else {
                if (browser.equalsIgnoreCase("CHROME")) {
                    driverInstance = new ChromeDriver(defaultChromeOptions());
                } else {
                    driverInstance = (WebDriver) driverClass.getDeclaredConstructor().newInstance();
                }
            }

        } catch (IllegalAccessException | ClassNotFoundException e) {
            System.err.println("The class could not be found");
            System.out.println(e);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            System.err.println("Problem during driver instantiation");
            System.out.println(e);
        }
        return driverInstance;
    }

    public static WebDriver defineHeadless(Class<?> driverClass) {
        WebDriver driver;

        if (ChromeDriver.class == driverClass) {
            driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
        } else if (FirefoxDriver.class == driverClass) {
            driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true).addArguments("-private"));
        } else {
            throw new IllegalArgumentException("Headless is only supported by Google Chrome or Firefox");
        }

        return driver;
    }


    private static ChromeOptions defaultChromeOptions() {
        return new ChromeOptions();
    }
}
