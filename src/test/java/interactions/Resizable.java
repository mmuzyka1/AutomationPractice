package interactions;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class Resizable extends BaseTest {

    @Test
    void shouldResizeElement() {
        Actions actions = new Actions(driver);
        driver.get("http://www.seleniumui.moderntester.pl/resizable.php");
        actions.dragAndDropBy(driver.findElement(By.cssSelector(".ui-resizable-e")), 10, 0).perform();
        actions.dragAndDropBy(driver.findElement(By.cssSelector(".ui-resizable-s")), 0, 10).perform();
        actions.dragAndDropBy(driver.findElement(By.cssSelector(".ui-resizable-se")), 10, 10).perform();
    }
}
