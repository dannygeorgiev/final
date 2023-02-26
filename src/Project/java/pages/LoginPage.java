package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    //Elements on the current page
    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    //Constructor
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Methods
    public ProductPage login(String userName, String password){
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new ProductPage(driver);
    }



}