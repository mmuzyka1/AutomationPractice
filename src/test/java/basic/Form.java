package basic;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Form extends BaseTest {

    Random random;

    @Test
    void shouldValidateForm() {
        driver.get("http://www.seleniumui.moderntester.pl/form.php");
        Random random = new Random();

        WebElement firstName = driver.findElement(By.cssSelector("#inputFirstName3"));
        firstName.click();
        firstName.sendKeys("Jan");

        WebElement lastName = driver.findElement(By.cssSelector("#inputLastName3"));
        lastName.click();
        lastName.sendKeys("Kowalski");

        WebElement email = driver.findElement(By.cssSelector("#inputEmail3"));
        email.click();
        email.sendKeys("jan.kowalski@test.pl");

        List<WebElement> sex = driver.findElements(By.cssSelector("[name='gridRadiosSex']"));
        sex.get(getRandomFromList(sex)).click();

        WebElement age = driver.findElement(By.cssSelector("#inputAge3"));
        age.click();
        age.sendKeys("27");

        List<WebElement> yearOfExperience = driver.findElements(By.cssSelector("[name='gridRadiosExperience']"));
        yearOfExperience.get(getRandomFromList(yearOfExperience)).click();

        driver.findElement(By.cssSelector("#gridCheckAutomationTester")).click();

        Select select = new Select(driver.findElement(By.cssSelector("#selectContinents")));
        select.selectByIndex(random.nextInt(select.getOptions().size()));

        select = new Select(driver.findElement(By.cssSelector("#selectSeleniumCommands")));
        select.selectByValue("switch-commands");
        select.selectByValue("wait-commands");

        File file = new File("src/main/resources/file.txt");
        driver.findElement(By.cssSelector(".custom-file-input")).sendKeys(file.getAbsolutePath());

        int length = getLength();
        driver.findElement(By.cssSelector(".btn-secondary")).click();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500));
        wait.until(x -> length + 1 == getLength());
        assertThat(getLength()).isEqualTo(length + 1);
        assertThat(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\test-file-to-download.xlsx").exists()).isTrue();
        driver.findElement(By.cssSelector("[type='submit']")).click();
        assertThat(driver.findElement(By.cssSelector("#validator-message"))
                .getText()).isEqualTo("Form send with success");
    }

    private int getRandomFromList(List list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }

    @AfterEach
    public void cleanDirectory() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\src\\test\\resources"));
    }

    private int getLength() {
        return Objects.requireNonNull(new File(System.getProperty("user.dir") + "\\src\\test\\resources").list()).length;
    }
}
