package basic;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Alerts extends BaseTest {

    @Test
    void shouldCheckSimpleAlertPopUp() {
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#simple-alert")).click();
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("#simple-alert-label")).getText()).isEqualTo("OK button pressed");
    }

    @Test
    void shouldCheckPromptAlertBox() {
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#prompt-alert")).click();
        driver.switchTo().alert().sendKeys("Chandler Bing");
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("#prompt-label"))
                .getText()).isEqualTo("Hello Chandler Bing! How are you today?");
    }

    @Test
    void shouldCheckConfirmAlertBoxConfirmed() {
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#confirm-alert")).click();
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("#confirm-label")).getText()).isEqualTo("You pressed OK!");
    }

    @Test
    void shouldCheckConfirmAlertBoxDismissed() {
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#confirm-alert")).click();
        driver.switchTo().alert().dismiss();
        assertThat(driver.findElement(By.cssSelector("#confirm-label")).getText()).isEqualTo("You pressed Cancel!");
    }

    @Test
    void shouldCheckDelayedAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#delayed-alert")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("#delayed-alert-label")).getText()).isEqualTo("OK button pressed");

    }
}
