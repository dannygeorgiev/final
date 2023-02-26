package Project;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginTests extends TestUtil {

    @Test (dataProvider = "correctUsers")
    public void successfulLogin(String userName, String password) throws InterruptedException {
        //WebElement usernameInput = driver.findElement(By.cssSelector("[name=\"username\"]"));
        WebElement usernameInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.name("SubmitLogin"));

        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(userName);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();

        //elements on products page
        //WebElement profileMenu = driver.findElement(By.id("react-burger-menu-btn"));
        WebElement myProfile = driver.findElement(By.cssSelector("[class=\"account\"));
        WebElement logoutButton = driver.findElement(By.cssSelector("class=\"logout\"));
        WebElement profilePageTitle = driver.findElement(By.xpath("//h1[text()='Моят профил']"));
        //WebElement productsPageTitle = driver.findElement(By.xpath("//span[text()='Products']"));
        //example of contains:
        //WebElement productsPageTitle = driver.findElement(By.xpath("//span[contains(text(),'Produc')]"));
        // //*[@id="item_4_title_link"]/div


        //soft assert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,1, "First Soft Assert");
        softAssert.assertEquals(1, 2, "Second Soft Assert");
        //maybe having some more stuff to check

        //Assert - Hard assertion
        Assert.assertTrue(logoutLink.isDisplayed(), "Logout link was not displayed");
        Assert.assertTrue(profilePageTitle.isDisplayed());
        Assert.assertEquals(profilePageTitle.getText(), "Моят профил");

        softAssert.assertAll();
    }

    //@Test (dataProvider = "wrongUsers")
   // public void unSuccessfulLogin(String userName, String password){
        //elements on login page
        //WebElement usernameInput = driver.findElement(By.cssSelector("[name=\"username\"]"));
        //WebElement usernameInput = driver.findElement(By.id("email"));
        //WebElement passwordInput = driver.findElement(By.id("password"));
       // WebElement loginBtn = driver.findElement(By.name("SubmitLogin"));


        //Actions
       // usernameInput.click();
       // usernameInput.clear();
       // usernameInput.sendKeys(userName);
      //  passwordInput.click();
      //  passwordInput.clear();
      //  passwordInput.sendKeys(password);
      //  loginBtn.click();

        //Assert
      //  WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
      //  Assert.assertEquals(errorMessage.getText(),
     //           "Epic sadface: Username and password do not match any user in this service");
//
  //  }

   // @DataProvider (name = "wrongUsers")
   // public Object [][] getUsers(){
   //     return new Object[][]{
   //             {"wrong", "secret_sauce"},
  //              {"standard_user", "wrong"},
  //              {"wrong", "wrong"}
  //      };
   // }

    @DataProvider (name = "correctUsers")
    public static Object [][] readUsersFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/Project/resources/correctUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[] [] csvDataObj = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++){
                csvDataObj[i] = csvData.get(i);
            }
            return csvDataObj;
        }catch (IOException e){
            System.out.println("Not Possible to find CSV!");
            return null;
        }
        catch (CsvException e){
            return null;
        }
    }
}
