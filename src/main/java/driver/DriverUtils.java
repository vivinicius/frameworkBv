package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DriverUtils {

    public List<WebElement> getElementsByText(WebDriver driver, String text){
        return driver.findElements(By.xpath("//*[contains(text()," + text +")]"));
    }

    public WebElement getElementByText(WebDriver driver, String text){
        return getElementsByText(driver, text).get(0);
    }


}
