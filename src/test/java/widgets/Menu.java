package widgets;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class Menu extends BaseTest {

    @Test
    void shouldCheckMenu() {
        Actions actions = new Actions(driver);
        driver.get("http://www.seleniumui.moderntester.pl/menu-item.php");
        actions.moveToElement(driver.findElement(By.cssSelector("#ui-id-9"))).perform();
        actions.moveToElement(driver.findElement(By.cssSelector("#ui-id-13"))).perform();
        driver.findElement(By.cssSelector("#ui-id-16")).click();
    }
}
