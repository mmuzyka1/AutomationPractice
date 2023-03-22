package other;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DemoQa extends BaseTest {

    @Test
    void shouldSelectMultipleOptions() {
        driver.get("https://demoqa.com/automation-practice-form");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement subjectsInputField = driver.findElement(By.cssSelector("#subjectsInput"));
        subjectsInputField.sendKeys("m");
        driver.findElement(By.cssSelector("#react-select-2-option-0")).click();
        subjectsInputField.sendKeys("a");
        driver.findElement(By.cssSelector("#react-select-2-option-2")).click();
        List<String> expectedSubjects = new ArrayList<>();
        expectedSubjects.add("Maths");
        expectedSubjects.add("Arts");
        List<WebElement> chosenSubjects = driver.findElements(By.cssSelector(".subjects-auto-complete__multi-value__label"));
        List<String> chosenSubjectsStrings = new ArrayList<>();
        for (WebElement subject : chosenSubjects) {
            chosenSubjectsStrings.add(subject.getText());
        }
        assertThat(chosenSubjectsStrings).isEqualTo(expectedSubjects);
    }

}
