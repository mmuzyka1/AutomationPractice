package interactions;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Collections;
import java.util.List;

public class Sortable extends BaseTest {

    @Test
    void shouldSortElements() {
        Actions actions = new Actions(driver);
        driver.get("http://www.seleniumui.moderntester.pl/sortable.php");
        List<WebElement> items = driver.findElements(By.cssSelector(".ui-state-default"));
        List<WebElement> shuffledItems = driver.findElements(By.cssSelector(".ui-state-default"));
        Collections.shuffle(shuffledItems);
        for (WebElement item : shuffledItems) {
            System.out.println(item.getText());
        }
        for (int i = 0; i < shuffledItems.size(); i++) {
            int position = Integer.parseInt(shuffledItems.get(i).getText().split(" ")[1]);
            if (position != i) {
                actions.dragAndDrop(shuffledItems.get(i), items.get(i)).perform();
                items = driver.findElements(By.cssSelector(".ui-state-default"));
            }
        }
    }
}
