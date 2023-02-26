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

        WebElement usernameInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("passwd"));
        WebElement loginBtn = driver.findElement(By.id("SubmitLogin"));

        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(userName);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();

        //elements on profile page

        WebElement myProfile = driver.findElement(By.className("account"));
        WebElement logoutButton = driver.findElement(By.className("logout"));
        WebElement profilePageTitle = driver.findElement(By.xpath("//h1[text()='Моят профил']"));


        //Asserts
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button was not displayed");
        Assert.assertTrue(profilePageTitle.isDisplayed(), "Profile page title was not displayed");
        Assert.assertEquals(profilePageTitle.getText(), "МОЯТ ПРОФИЛ");
    }

    @Test (dataProvider = "wrongUsers")
    public void unSuccessfulLogin(String userName, String password){
    //elements on login page
    WebElement usernameInput = driver.findElement(By.id("email"));
    WebElement passwordInput = driver.findElement(By.id("passwd"));
    WebElement loginBtn = driver.findElement(By.id("SubmitLogin"));


    //Actions
     usernameInput.click();
     usernameInput.clear();
     usernameInput.sendKeys(userName);
      passwordInput.click();
      passwordInput.clear();
      passwordInput.sendKeys(password);
      loginBtn.click();

    //Elements
      WebElement errorMessage = driver.findElement(By.xpath("//p[text()='Възникна 1 грешка']"));

    //Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));



    //Assert
      Assert.assertEquals(errorMessage.getText(), "Възникна 1 грешка");
    }

     @DataProvider (name = "wrongUsers")
     public Object [][] getUsers(){
         return new Object[][]{
                 {"dannytest@mail.bg", "wrong"}
          };
     }

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
