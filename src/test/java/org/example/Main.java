package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Main {

    private WebDriver webDriver;

    @BeforeMethod
    public void before() {
        boolean logged = false;

        while (true) {
            try {
                Calendar c1 = Calendar.getInstance();
                TimeUnit.SECONDS.sleep(60);

                int day = c1.get(Calendar.DAY_OF_WEEK);
                int hour = c1.get(Calendar.HOUR_OF_DAY);
                int minute = c1.get(Calendar.MINUTE);
                int sec = c1.get(Calendar.SECOND);

                System.out.println(day + " " + hour + ":" + minute + ":" + sec);

                if (day >= 2 && day <= 6 && hour >= 5 && hour < 14 && minute >= 58 && !logged) { // set time to login
                    logged = true;
                    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                    webDriver = new ChromeDriver();
                    webDriver.navigate().to("https://inewi.pl/");
                    webDriver.findElement(By.className("login")).click();

                    WebDriverWait wait = new WebDriverWait(webDriver, 20);
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-wq2bsh")));

                    webDriver.findElement(By.name("email")).sendKeys("krzymowski.b@gmail.com");
                    webDriver.findElement(By.name("password")).sendKeys("zaq1@WSX");
                    webDriver.findElement(By.className("css-wq2bsh")).click();

                    // LOGIN
                    WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-p85ksb")));
                    webDriver.findElement(By.className("css-p85ksb")).click();
                    System.out.println("ZALOGOWANO " + day + "ego dnia tygodnia o godzinie: " + hour + ":" + minute + ":" + sec);
                }

                if (day >= 2 && day <= 6 && hour >= 14 && minute >= 0 && logged) { // set time to logout
                    logged = false;
                    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                    webDriver = new ChromeDriver();
                    webDriver.navigate().to("https://inewi.pl/");
                    webDriver.findElement(By.className("login")).click();

                    WebDriverWait wait = new WebDriverWait(webDriver, 20);
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-wq2bsh")));

                    webDriver.findElement(By.name("email")).sendKeys(""); // set E-mail
                    webDriver.findElement(By.name("password")).sendKeys(""); // set Password
                    webDriver.findElement(By.className("css-wq2bsh")).click();

                    // LOGOUT
                    WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-p85ksb")));
                    webDriver.findElement(By.className("css-p85ksb")).click();

                    WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-1krog6x")));
                    webDriver.findElement(By.className("css-1krog6x")).click();

                    System.out.println("WYLOGOWANO " + day + "ego dnia tygodnia o godzinie: " + hour + ":" + minute + ":" + sec);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void test() {

    }

    public static void main(String args[]) {

    }
}