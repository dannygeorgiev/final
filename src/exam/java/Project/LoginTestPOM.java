package Project;

import base.TestUtil;
import org.testng.annotations.Test;
import pages.LoginPage;


public class LoginTestPOM extends TestUtil {

    @Test
    public void successfulLogin(String userName, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
    }
}
