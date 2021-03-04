package page.objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static driver.DriverManager.getDriver;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginPage extends AbstractPageObject {

    private WebDriverWait wait;

    @FindBy(id = "cpf")
    private WebElement cpf;

    @FindBy(id = "data")
    private WebElement birthDate;

    @FindBy(id = "senha")
    private WebElement password;

    @FindBy(xpath = "//iframe[contains(@src,'https://www.google.com/recaptcha/api2/anchor')]")
    protected WebElement recaptchaIFrame;

    @FindBy(id = "recaptcha-anchor")
    protected WebElement recaptcha;

    @FindBy(id = "avancar_passo_cnpj")
    private WebElement loginBtn;

    @FindBy(css = ".primary-title")
    private WebElement pageTitle;

    @FindBy(css = ".label-error")
    private WebElement errorMsg;

    @FindBy(css = "div.form-field.w-100.error")
    private List<WebElement> errorInputList;

    public static LoginPage action() {
        return new LoginPage();
    }

    public LoginPage fillCpf(String cpf) {
        this.cpf.sendKeys(cpf);
        return new LoginPage();
    }

    public LoginPage fillBirthDate(String birthDate) {
        this.birthDate.sendKeys(birthDate);
        return new LoginPage();
    }

    public LoginPage fillPassword(String password) {
        this.password.sendKeys(password);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].type='text';", this.password);
        return new LoginPage();
    }

    public LoginPage clickRecaptcha() throws InterruptedException {
        getDriver().switchTo().frame(recaptchaIFrame);
        recaptcha.click();
        getDriver().switchTo().defaultContent();
        return new LoginPage();
    }

    public LoginPage clickLoginBtn() throws InterruptedException {
        wait = new WebDriverWait(getDriver(), ofSeconds(3));
        wait.until(attributeContains(loginBtn, "class", "primary"));
//        loginBtn.click();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", loginBtn);
        return new LoginPage();
    }

    public boolean isPageTitleDisplayed() {
        return pageTitle.isDisplayed();
    }

    public String getCpfBorderColor() {
        return cpf.getCssValue("border-color");
    }

    public String getBirthDateBorderColor() {
        return birthDate.getCssValue("border-color");
    }

    public String getPasswordBorderColor() {
        return password.getCssValue("border-color");
    }

    public String getErrorMsgText() {
        return errorMsg.getAttribute("innerText");
    }

    public LoginPage waitForErrorMsg() {
        wait = new WebDriverWait(getDriver(), ofSeconds(3));
        errorInputList.forEach(errorInput ->
                wait.until(attributeContains(errorInput, "data-bind", "hasErros")));
        wait.until(visibilityOf(errorMsg));
        wait.until(attributeContains(loginBtn, "class", "disable"));
        if (!getBirthDateBorderColor().equals("rgb(215, 69, 77)")){
            long start = System.currentTimeMillis();
            long end = start + 1000;
            while(!getBirthDateBorderColor().equals("rgb(215, 69, 77)")) {
                if(System.currentTimeMillis() > end) {
                    break;
                }
            }
        }
        return new LoginPage();
    }
}
