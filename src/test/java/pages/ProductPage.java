package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {
    private final static String PRODUCT_ID = "add-to-cart-sauce-labs-";

    //Elements
    @FindBy(xpath = "//span[@class='title']")
    WebElement productsPageHeading;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    WebElement cartBadge;

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addItemToTheCart(String itemName){
        WebElement itemToBeAdded = driver.findElement(By.id(PRODUCT_ID + itemName));
        itemToBeAdded.click();
    }

    public int getItemsInTheCart(){
        return Integer.parseInt(cartBadge.getText());
    }
}

