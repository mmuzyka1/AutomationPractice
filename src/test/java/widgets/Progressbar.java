package widgets;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Progressbar extends BaseTest {


    @Test
    void shouldCheckProgressBarLabel() {
        driver.get("http://www.seleniumui.moderntester.pl/progressbar.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector(".progress-label")), "Complete!"));
    }

    @Test
    void shouldCheckProgressBarAttribute() {
        driver.get("http://www.seleniumui.moderntester.pl/progressbar.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector(".ui-progressbar-value"), "class", "ui-progressbar-complete"));
    }
}
