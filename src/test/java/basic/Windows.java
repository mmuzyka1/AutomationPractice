package basic;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class Windows extends BaseTest {

    private String originalWindow;

    @Test
    void shouldOperateOnBrowser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://www.seleniumui.moderntester.pl/windows-tabs.php");
        originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        driver.findElement(By.cssSelector("#newBrowserWindow")).click();
        wait.until(numberOfWindowsToBe(2));
        handleWindow();
        printRows();
        switchToDefaultContent();

        driver.findElement(By.cssSelector("#newMessageWindow")).click();
        handleWindow();
        System.out.println(driver.findElement(By.xpath("//body")).getText());
        switchToDefaultContent();

        driver.findElement(By.cssSelector("#newBrowserTab")).click();
        switchToNewTab();
        printRows();

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    private void printRows() {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
        for(WebElement row : rows) {
            if(Integer.parseInt(row.findElement(By.xpath("./td[4]")).getText()) > 4000 && row.findElement(By.xpath("./td[3]")).getText().contains("Switzerland")) {
                System.out.print(row.findElement(By.cssSelector("th")).getText() + " ");
                System.out.print(row.findElement(By.xpath("./td[1]")).getText() + " ");
                System.out.print(row.findElement(By.xpath("./td[2]")).getText() + " \n");
            }
        }
    }

    private void switchToDefaultContent() {
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    private void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    private void handleWindow() {
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

}
