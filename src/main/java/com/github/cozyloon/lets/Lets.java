//
// (author:Chathumal Sangeeth)
//
package com.github.cozyloon.lets;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Lets {
    static WebDriver driver;

    public static void launchDriver(String browserType) {
        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        if (browserType.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
        if (browserType.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
            driver.manage().window().maximize();
        }
    }

    public static void closeDriver() {
        driver.quit();
    }

    public static void getUrl(String url) {
        driver.get(url);
    }

    public static void back() {
        driver.navigate().back();
    }

    public static void forward() {
        driver.navigate().forward();
    }

    public static void refresh() {
        driver.navigate().refresh();
    }

    public static void click(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public static void type(By locator, String text) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public static void selectDrpDwnByVisibleText(By locator, String text) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public static void selectDrpDwnByVisibleValue(By locator, String value) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void selectDrpDwnByIndex(By locator, int index) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public static void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public static void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public static void mouseHoverAndClick(By locator, long waitTimeInSeconds) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds)).until(ExpectedConditions.elementToBeClickable(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public static boolean elementIsDisplayed(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.isDisplayed();
    }

    public static boolean elementIsSelected(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
        return element.isSelected();
    }

    public static String getText(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public static void scrollToPageBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToPageTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToElement(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void javaScriptClick(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void highLightTheElement(By locator) throws InterruptedException {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        javascript.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid black;');", element);
        Thread.sleep(500);
        javascript.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
    }

    public static void takePageScreenShot() throws IOException {
        Screenshot screenshot = (new AShot()).shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        File f = new File("./ScreenShots/");
        Date date;
        SimpleDateFormat dateFormat;
        if (!f.exists() || !f.isDirectory()) {
            (new File("./ScreenShots/")).mkdir();
        }
        date = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        ImageIO.write(screenshot.getImage(), "jpg", new File("./ScreenShots/" + dateFormat.format(date) + ".jpg"));
    }

    public static void waitTillElementToBeVisible(By locator, long waitTimeInSecond) throws InterruptedException {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSecond)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.wait();
    }

    public static void waitTillElementToBeClickable(By locator, long waitTimeInSecond) throws InterruptedException {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSecond)).until(ExpectedConditions.elementToBeClickable(locator));
        element.wait();
    }

    public static void fileUploadChooser(By locator, String filePath) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(filePath);
    }
}
