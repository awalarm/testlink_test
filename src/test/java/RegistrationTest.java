import base.BaseTest;
import utils.TestUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegistrationTest extends BaseTest {

    final static String userLogin = "usertest123rt";
    final static String userPassword = "0987";

    public static void loginCorrect(){
        getWebDriver().get("http://testlink.testbase.ru/login.php");

        getWebDriver().findElement(By.id("tl_login")).sendKeys(userLogin);
        getWebDriver().findElement(By.id("tl_password")).sendKeys(userPassword);

        WebElement loginButton = getWebDriver().findElement(By.id("tl_login_button"));
        TestUtils.scrollAndClick(getWebDriver(), loginButton);
    }

    @Test
    public void signupTest(){
        getWebDriver().get("http://testlink.testbase.ru/login.php");

        getWebDriver().findElement(By.id("tl_sign_up")).click();

        getWebDriver().findElement(By.id("login")).sendKeys(userLogin);
        getWebDriver().findElement(By.id("password")).sendKeys(userPassword);
        getWebDriver().findElement(By.id("password2")).sendKeys(userPassword);
        getWebDriver().findElement(By.id("firstName")).sendKeys("User1testrt");
        getWebDriver().findElement(By.id("lastName")).sendKeys("User2testrt");
        getWebDriver().findElement(By.id("email")).sendKeys("rt@example.com");

        WebElement registrationConfirmationButton = getWebDriver().findElement(By.name("doEditUser"));
        TestUtils.scrollAndClick(getWebDriver(), registrationConfirmationButton);

        assertEquals(getWebDriver().findElement(By.className("user__feedback")).getText(), "Welcome to TestLink! You have guest access now. Please login ...");
    }

    @Test(dependsOnMethods = "signupTest")
    public void loginTest() {
        loginCorrect();

        assertEquals(getWebDriver().getTitle(), "TestLink 1.9.20 [DEV]");
    }
}
