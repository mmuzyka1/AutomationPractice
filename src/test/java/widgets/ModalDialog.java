package widgets;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ModalDialog {

    private ChromeDriver driver;

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {"Adam Nowak", "anowak@gmail.com", "12345"}
        };
    }

    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test(dataProvider = "test-data")
    void shouldCreateAccount(String name, String email, String password) {
        setUp();
        driver.get("http://www.seleniumui.moderntester.pl/modal-dialog.php");
        driver.findElement(By.cssSelector("#create-user")).click();

        WebElement nameInput = driver.findElement(By.cssSelector("#name"));
        nameInput.clear();
        nameInput.sendKeys(name);

        WebElement emailNInput = driver.findElement(By.cssSelector("#email"));
        emailNInput.clear();
        emailNInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        driver.findElements(By.cssSelector(".ui-dialog-buttonset .ui-button")).get(0).click();

        assertThat(driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText()).isEqualTo(name);
        assertThat(driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText()).isEqualTo(email);
        assertThat(driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText()).isEqualTo("xxxxx");
        driver.quit();
    }
}


