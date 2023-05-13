package actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.ExcelDPMain;

public class WebActions {
	public static WebDriverWait wait = new WebDriverWait(ExcelDPMain.getDriver(), Duration.ofSeconds(5));
    public static WebElement waitVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static void writeText(By by, String text) {
        waitVisibility(by).sendKeys(text);
    }
    public static String readText(By by) {
        return waitVisibility(by).getText();
    }
    public static void click(By by) {
        waitVisibility(by).click();
    }
    public static void clear(By by) {
        waitVisibility(by).clear();
    }
}
