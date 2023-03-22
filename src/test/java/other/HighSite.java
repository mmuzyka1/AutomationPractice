package other;

import basic.BaseTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class HighSite extends BaseTest {

    WebElement submitButton;
    Actions actions;

    @Test
    void shouldCheckScrollingWithActionClass() throws Exception {
        driver.get("http://www.seleniumui.moderntester.pl/high-site.php");
        actions = new Actions(driver);
        scrollUntilVisible();
        takeSnapShot(driver, "src/main/resources/screenshot.png");
    }

    @Test
    public void shouldScrollWithJSExecutor() throws Exception {
        driver.get("http://www.seleniumui.moderntester.pl/high-site.php");
        scrollUntilVisibleJSExecutor();
        takeSnapShot(driver, "src/main/resources/screenshotJS.png");
    }

    private void scrollUntilVisible() {
        while (true) {
            actions.scrollByAmount(0, 100).perform();
           try {driver.findElement(By.cssSelector("#scroll-button")).click();
               break;
            } catch (NoSuchElementException e){
           }
        }
    }

    private void scrollUntilVisibleJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (true) {
            js.executeScript("window.scrollBy(0,100)", "");
            try {driver.findElement(By.cssSelector("#scroll-button")).click();
                break;
            } catch (NoSuchElementException e){
            }
        }
    }

    public static void takeSnapShot(WebDriver driver,String fileWithPath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
