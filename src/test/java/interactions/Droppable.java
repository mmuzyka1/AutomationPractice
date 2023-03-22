package interactions;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Droppable extends BaseTest {

    @Test
    void shouldCheckDroppable() {
        driver.get("http://www.seleniumui.moderntester.pl/droppable.php");
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(By.cssSelector("#draggable")), driver.findElement(By.cssSelector("#droppable")))
                .perform();
        assertThat(driver.findElement(By.cssSelector("#droppable>p")).getText()).isEqualTo("Dropped!");
    }
}
