package interactions;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Draggable extends BaseTest {

    @Test
    void shouldDragElement() {
        Actions actions = new Actions(driver);
        driver.get("http://www.seleniumui.moderntester.pl/draggable.php");
        System.out.println(driver.manage().window().getSize());
        WebElement element = driver.findElement(By.cssSelector("#draggable"));
        System.out.println(element.getLocation());
        System.out.println(element.getSize().getHeight());
        System.out.println(element.getSize().getWidth());
        int windowWidth = driver.manage().window().getSize().getWidth();
        int windowHeight = driver.manage().window().getSize().getHeight();
        int elementWidth = element.getSize().getWidth();
        int elementHeight = element.getSize().getHeight();
        int elementLocationX = element.getLocation().x;
        int elementLocationY = element.getLocation().y;
        actions.dragAndDropBy(element, (windowWidth - elementWidth - elementLocationX), -elementLocationY).perform();
        actions.dragAndDropBy(element, 0, windowHeight - elementLocationY - elementHeight / 2).perform();
        actions.dragAndDropBy(element, -(windowWidth / 2 - elementWidth / 2), -(windowHeight / 2 - elementHeight / 2))
                .perform();
        actions.dragAndDropBy(element, -(windowWidth / 2 - elementWidth / 2), (windowHeight / 2 - elementHeight / 2))
                .perform();
    }
}
