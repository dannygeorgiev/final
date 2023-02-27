package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    //Elements
    @FindBy(id = "search-field")
    WebElement searchBox;

    @FindBy(xpath = "//a[contains(text(), '%s')]")
    WebElement productTitle;

    @FindBy(className = "btn=buy")
    WebElement addToCartButton;


    @FindBy(xpath = "//div[@class='text']")
    WebElement cartItemCount;



    public ProductPage(WebDriver driver, String productName){
        super(driver);
        PageFactory.initElements(driver, this);
        productTitle = driver.findElement(By.xpath(String.format("//a[contains(text(), '%s')]", productName)));
    }


    public void searchForProduct(String productName) {
        searchBox.sendKeys(productName);
        searchBox.submit();
    }

    public void addToCart() {
        addToCartButton.click();
    }
    public String getCartItemCount() {
        String itemCountText = cartItemCount.getText().trim();
        return itemCountText;
    }
    public String getChosenProduct() {
        String chosenProduct = productTitle;
        return chosenProduct;

    }
}

