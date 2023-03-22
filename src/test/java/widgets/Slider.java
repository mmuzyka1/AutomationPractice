package widgets;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Slider extends BaseTest {

    private WebElement slider;

    @Test
    void shouldCheckSlider() {
        driver.get("http://www.seleniumui.moderntester.pl/slider.php");
        slider = driver.findElement(By.cssSelector("#custom-handle"));
        moveSlider(50);
        moveSlider(80);
        moveSlider(80);
        moveSlider(20);
        moveSlider(0);
    }

    private void moveSlider(int value) {
        Actions actions = new Actions(driver);
        while (getSliderValue() < value) {
            slider.click();
            actions.sendKeys(Keys.ARROW_RIGHT).perform();
        }
        while (getSliderValue() > value) {
            slider.click();
            actions.sendKeys(Keys.ARROW_LEFT).perform();
        }
    }

    private int getSliderValue() {
        return Integer.parseInt(slider.getText());
    }
}
