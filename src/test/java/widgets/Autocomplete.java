package widgets;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Autocomplete extends BaseTest {

    @Test
    void shouldCheckAutocomplete() {
        Random random = new Random();
        driver.get("http://www.seleniumui.moderntester.pl/autocomplete.php");
        driver.findElement(By.cssSelector("#search")).sendKeys("a");
        List<WebElement> options = driver.findElements(By.cssSelector(".ui-menu-item"));
        for(WebElement option : options) {
            System.out.println(option.getText());
        }
        WebElement optionToChoose = options.get(random.nextInt(options.size()-1));
        optionToChoose.click();
        assertThat(driver.findElement(By.cssSelector("#search")).getAttribute("innerText")).isEqualTo(optionToChoose.getText());
    }
}
