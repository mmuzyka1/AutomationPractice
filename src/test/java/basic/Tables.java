package basic;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Tables extends BaseTest {

    @Test
    void shouldPrintRows() {
        driver.get("http://www.seleniumui.moderntester.pl/table.php");
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
        for (WebElement row : rows) {
            if (Integer.parseInt(row.findElement(By.xpath("./td[4]"))
                    .getText()) > 4000 && row.findElement(By.xpath("./td[3]")).getText().contains("Switzerland")) {
                System.out.print(row.findElement(By.cssSelector("th")).getText() + " ");
                System.out.print(row.findElement(By.xpath("./td[1]")).getText() + " ");
                System.out.print(row.findElement(By.xpath("./td[2]")).getText() + " \n");
            }
        }
    }
}
