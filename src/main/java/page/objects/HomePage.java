package page.objects;

import org.openqa.selenium.support.ui.WebDriverWait;

import static driver.DriverManager.getDriver;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class HomePage extends AbstractPageObject {

    private WebDriverWait wait;

    public static HomePage action(){
        return new HomePage();
    }

    public boolean isHomePageDisplayed() {
        wait = new WebDriverWait(getDriver(), ofSeconds(40));
        wait.until(urlContains("home"));
        return getDriver().getCurrentUrl().contains("home");
    }
}
