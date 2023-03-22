package widgets;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class Tooltip extends BaseTest {

    @Test
    void shouldPrintTooltipText() {
        Actions actions = new Actions(driver);
        driver.get("http://www.seleniumui.moderntester.pl/tooltip.php");
        actions.moveToElement(driver.findElement(By.xpath("//a[@title=\"That's what this widget is\"]"))).perform();
        System.out.println(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText());
    }
}
