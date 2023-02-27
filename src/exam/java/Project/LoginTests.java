package Project;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LoginTests extends TestUtil {

    @Test (dataProvider = "correctUsers")
    public void successfulLogin(String userName, String password) throws InterruptedException {


        WebElement loginIcon = driver.findElement(By.className("s-profile"));

        loginIcon.click();


        WebElement loginPageTitle = driver.findElement(By.xpath("//p[text()='Вход в eBag']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPageTitle));

        WebElement usernameInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.className("login-btn"));

        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(userName);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();

        //Actions actions = new Actions(driver);
        //WebElement profileIconToHoverOver = driver.findElement(By.className("profile-container"));
        //actions.moveToElement(profileIconToHoverOver).perform();

        WebElement profileDropdown = driver.findElement(By.xpath("//div[@class='profile-dropdown has-dd']"));
        WebElement bannersCarrousel = driver.findElement(By.className("banners-container"));


        //Asserts
        Assert.assertTrue(profileDropdown.isDisplayed(), "Profile dropdown menu was not displayed");
        Assert.assertTrue(bannersCarrousel.isDisplayed(), "Banners carrousel was not displayed");
    }

    @Test (dataProvider = "wrongUsers")
    public void unSuccessfulLogin(String userName, String password){

        WebElement loginIcon = driver.findElement(By.className("s-profile"));

        loginIcon.click();


        WebElement loginPageTitle = driver.findElement(By.xpath("//p[text()='Вход в eBag']"));
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait3.until(ExpectedConditions.visibilityOf(loginPageTitle));

        WebElement usernameInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.className("login-btn"));


        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(userName);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();


      WebElement errorMessage = driver.findElement(By.xpath("//li[text()='Невалидно име/парола']"));

      WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait4.until(ExpectedConditions.visibilityOf(errorMessage));


       Assert.assertEquals(errorMessage.getText(), "Невалидно име/парола");
    }

     @DataProvider (name = "wrongUsers")
     public Object [][] getUsers(){
         return new Object[][]{
                 {"dannytest@mail.bg", "dwadadawdasda"}
          };
     }

    @DataProvider (name = "correctUsers")
    public static Object [][] readUsersFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/exam/resources/correctUsers.csv"));
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
