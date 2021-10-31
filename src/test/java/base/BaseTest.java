package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private static WebDriver webDriver;

    @BeforeClass
    protected void before() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected void beforeTest(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    protected void afterTest(){
        webDriver.quit();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }
}
