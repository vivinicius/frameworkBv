package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginBVPage extends AbstractPageObject {

    @FindBy(id = "bv-header")
    protected WebElement bvHeader;

    public boolean getBVIcon() {
        return bvHeader.isDisplayed();
    }
}
