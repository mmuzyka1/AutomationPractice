package widgets;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class Selectable extends BaseTest {

    @Test
    void shouldCheckSelectablePage() {
        Random random = new Random();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://www.seleniumui.moderntester.pl/selectmenu.php");
        Select number = new Select(driver.findElement(By.cssSelector("#number")));
        number.selectByIndex(1);
        Select salutation = new Select(driver.findElement(By.cssSelector("#salutation")));
        salutation.selectByIndex(random.nextInt(salutation.getOptions().size() - 1));
    }
}
