package interactions;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

public class Selectable extends BaseTest {

    @Test
    void shouldCheckSelectable() {
        Actions actions = new Actions(driver);
        driver.get("http://www.seleniumui.moderntester.pl/selectable.php");
        List<WebElement> options = driver.findElements(By.cssSelector(".ui-selectee"));
        actions.keyDown(Keys.CONTROL)
                .click(options.get(0))
                .click(options.get(2))
                .click(options.get(3))
                .perform();
        assertThat("You've selected: " + driver.findElement(By.cssSelector("#select-result")).getText() + ".").isEqualTo("You've selected: #1 #3 #4.");
    }
}
