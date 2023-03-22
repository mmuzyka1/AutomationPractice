package widgets;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Accordion extends BaseTest {

    @Test
    void shouldPrintText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://www.seleniumui.moderntester.pl/accordion.php");
        System.out.println(driver.findElement(By.cssSelector("#ui-id-2 p")).getText());

        WebElement sectionTwo = driver.findElement(By.cssSelector("#ui-id-3"));
        wait.until(ExpectedConditions.elementToBeClickable(sectionTwo));
        sectionTwo.click();
        WebElement sectionTwoContent = driver.findElement(By.cssSelector("#ui-id-4 p"));
        wait.until(ExpectedConditions.visibilityOf(sectionTwoContent));
        System.out.println(sectionTwoContent.getText());

        WebElement sectionThree = driver.findElement(By.cssSelector("#ui-id-5"));
        wait.until(ExpectedConditions.elementToBeClickable(sectionThree));
        sectionThree.click();
        WebElement sectionThreeContent = driver.findElement(By.cssSelector("#ui-id-6 p"));
        wait.until(ExpectedConditions.visibilityOf(sectionThreeContent));
        System.out.println(sectionThreeContent.getText());
        System.out.println(driver.findElement(By.cssSelector("#ui-id-6 ul")).getText());

        WebElement sectionFour = driver.findElement(By.cssSelector("#ui-id-7"));
        wait.until(ExpectedConditions.elementToBeClickable(sectionFour));
        sectionFour.click();
        List<WebElement> sectionFourContent = driver.findElements(By.cssSelector("#ui-id-8 p"));
        wait.until(ExpectedConditions.visibilityOf(sectionFourContent.get(sectionFourContent.size() - 1)));
        for (WebElement element : sectionFourContent) {
            System.out.println(element.getText());
        }
    }
}
