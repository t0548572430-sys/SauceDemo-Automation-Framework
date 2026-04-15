package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"user_not_exist", "wrong_password", "Username and password do not match"},
                {"locked_out_user", "secret_sauce", "Sorry, this user has been locked out"},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"}
        };
    }

    @Test(dataProvider = "loginData", description = "Testing connection error scenarios (Data-Driven)")
    public void invalidLoginScenario(String user, String pass, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(user, pass);

        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
                "The error message is not matching! We expected to detect:" + expectedError);
    }
}