package widgets;

import basic.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Datepicker extends BaseTest {

    @Test
    void shouldCheckDates() throws ParseException, InterruptedException {
        driver.get("http://www.seleniumui.moderntester.pl/datepicker.php");

        selectDate(getTodaysDateAsString());
        assertThat(getTodaysDateAsString()).isEqualTo(getDisplayedDate());

        selectDate(getDateFromNextMonthAsString());
        assertThat(getDateFromNextMonthAsString()).isEqualTo(getDisplayedDate());

        selectDate(getJanuaryNextYearDateAsString());
        assertThat(getJanuaryNextYearDateAsString()).isEqualTo(getDisplayedDate());

        selectDate(getJanuaryNextYearDateAsString());
        assertThat(getJanuaryNextYearDateAsString()).isEqualTo(getDisplayedDate());

        String randomDateFromPreviousMonth = getRandomDateFromPreviousMonth();
        selectDate(randomDateFromPreviousMonth);
        assertThat(randomDateFromPreviousMonth).isEqualTo(getDisplayedDate());

        String randomDateFromLastYear = getRandomDateFromLastYear();
        selectDate(randomDateFromLastYear);
        assertThat(randomDateFromLastYear).isEqualTo(getDisplayedDate());
    }

    private void selectDate(String targetDate) throws ParseException {
        openDatepicker();

        Date date1 = getStringAsDate(targetDate);
        Date date2 = getElementAsDate(driver.findElement(By.cssSelector(".ui-datepicker-title")).getText());
        String singleDay = getSingleDayFromDate(targetDate);

        while (true) {
            if (date1.compareTo(date2) < 0) {
                driver.findElement(By.cssSelector(".ui-datepicker-prev")).click();
                date2 = getElementAsDate(driver.findElement(By.cssSelector(".ui-datepicker-title")).getText());
            } else if (date1.compareTo(date2) > 0) {
                driver.findElement(By.cssSelector(".ui-datepicker-next")).click();
                date2 = getElementAsDate(driver.findElement(By.cssSelector(".ui-datepicker-title")).getText());
            } else {
                driver.findElements(By.cssSelector("a[class='ui-state-default'], .ui-state-highlight, .ui-state-active"))
                        .get(Integer.parseInt(singleDay) - 1)
                        .click();
                break;
            }
        }
    }

    private void openDatepicker() {
        driver.findElement(By.cssSelector("#datepicker")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".ui-datepicker-title"))));
    }

    private Date getStringAsDate(String dateAsString) throws ParseException {
        DateFormat givenFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat expectedFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        Date date = givenFormat.parse(dateAsString);
        String formattedDate = expectedFormat.format(date);
        return expectedFormat.parse(formattedDate);
    }

    private Date getElementAsDate(String dateAsString) throws ParseException {
        DateFormat givenFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        DateFormat expectedFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = givenFormat.parse(dateAsString);
        String formattedDate = expectedFormat.format(date);
        return expectedFormat.parse(formattedDate);
    }

    private String getSingleDayFromDate(String date) {
        return date.split("/")[1].split("/")[0];
    }

    private String getTodaysDateAsString() {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateObj.format(formatter);
    }

    private String getDateFromNextMonthAsString() {
        LocalDate dateObj = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateObj.format(formatter);
    }

    private String getJanuaryNextYearDateAsString() {
        LocalDate dateObj = LocalDate.now().plusYears(1).withMonth(1).withDayOfMonth(31);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateObj.format(formatter);
    }

    private String getRandomDateFromPreviousMonth() {
        Random random = new Random();
        LocalDate initial = LocalDate.now().minusMonths(1);
        LocalDate dateObj = LocalDate.now()
                .minusMonths(1)
                .withDayOfMonth(random.nextInt(initial.getMonth().length(initial.isLeapYear())));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateObj.format(formatter);
    }

    private String getRandomDateFromLastYear() {
        Random random = new Random();
        LocalDate initial = LocalDate.now().minusYears(1);
        int numberOfDays = 365;
        if (initial.isLeapYear()) {
            numberOfDays = 366;
        }
        LocalDate dateObj = LocalDate.now().minusYears(1).withDayOfYear(random.nextInt(numberOfDays));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateObj.format(formatter);
    }

    private String getDisplayedDate() {
        return driver.findElement(By.cssSelector("#datepicker")).getAttribute("value");
    }
}
