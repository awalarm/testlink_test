import base.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.TestUtils.jsClick;
import static utils.TestUtils.scrollAndClick;

public class TestCaseTest extends BaseTest {

    private static final String NAME_TEST_SUITE = "zzzTestSuiteTest";
    private static final String NAME_TEST_CASE = "TestcaseTest";

    private void switchProject(){
        getWebDriver().switchTo().frame(getWebDriver().findElement(By.name("titlebar")));

        jsClick(getWebDriver(), getWebDriver().findElement(By.name("productForm")));
        getWebDriver().findElement(By.xpath("//option[@title='Test:Test']")).click();

        getWebDriver().switchTo().defaultContent();
    }

    private void switchToSpec() {
        switchProject();

        getWebDriver().switchTo().frame(getWebDriver().findElement(By.name("mainframe")));
        getWebDriver().findElement(By.xpath("//a[contains(text(),'Test Specification')]")).click();
    }

    private void switchToWorkframe() {
        getWebDriver().switchTo().parentFrame();
        getWebDriver().switchTo().frame(getWebDriver().findElement(By.name("workframe")));
    }

    private void createNewTestCase() {
        switchToWorkframe();

        getWebDriver().findElement(By.cssSelector(".clickable:nth-child(2)")).click();
        getWebDriver().findElement(By.name("create_tc")).click();
    }


    private void switchToTreeframe() {
        getWebDriver().switchTo().defaultContent();
        getWebDriver().switchTo().frame(getWebDriver().findElement(By.name("mainframe")));
        getWebDriver().switchTo().frame(getWebDriver().findElement(By.name("treeframe")));
    }

    @Test
    public void createdTestSuiteTest(){
        RegistrationTest.loginCorrect();

        switchToSpec();

        getWebDriver().switchTo().frame(getWebDriver().findElement(By.name("workframe")));

        getWebDriver().findElement(By.cssSelector(".clickable:nth-child(1)")).click();
        getWebDriver().findElement(By.id("new_testsuite")).click();

        getWebDriver().findElement(By.id("name")).sendKeys(NAME_TEST_SUITE);
        getWebDriver().findElement(By.name("add_testsuite_button")).click();

        switchToTreeframe();

        Assert.assertTrue(getWebDriver().findElement(By.xpath("//span[contains(text(),'zzzTestSuiteTest')]")).getText().contains(NAME_TEST_SUITE));
    }

    @Test(dependsOnMethods = "createdTestSuiteTest")
    public void createdTestCaseTest() {
        RegistrationTest.loginCorrect();

        switchToSpec();

        switchToTreeframe();

        scrollAndClick(getWebDriver(), getWebDriver().findElement(By.xpath("//span[contains(text(),'zzzTestSuiteTest')]")));

        createNewTestCase();

        getWebDriver().findElement(By.id("testcase_name")).sendKeys(NAME_TEST_CASE);
        getWebDriver().findElement(By.id("do_create_button")).click();

        switchToTreeframe();
        getWebDriver().findElement(By.cssSelector(".x-tree-elbow-end-plus")).click();

        Assert.assertTrue(getWebDriver().findElement(By.xpath("//span[contains(text(),'TestcaseTest')]")).getText().contains(NAME_TEST_CASE));
    }
}
