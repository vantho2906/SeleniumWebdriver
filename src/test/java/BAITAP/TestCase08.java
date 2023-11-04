package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
public class TestCase08 {
    @Test
    public static void testcase08(){
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        String dirPath = "screenshots";
        File dir = new File(dirPath);
        //check dir exists
        if (!dir.exists()) {
            dir.mkdir();
        }
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        try{
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on my account link
            driver.findElement(By.cssSelector(".skip-link.skip-account")).click();
            driver.findElement(By.cssSelector("div[id='header-account'] a[title='My Account']")).click();
            //debug purpose only
            Thread.sleep(2000);

            //3. Login in application using previously created credential
            driver.findElement(By.id("email")).sendKeys("07linhnguyen@gmail.com");
            driver.findElement(By.id("pass")).sendKeys("123456");
            driver.findElement(By.id("send2")).click();
            Thread.sleep(2000);

            //4. Click on 'REORDER' link , change QTY & click Update
            driver.findElement(By.xpath("//tr[@class='last even']//a[@class='link-reorder'][normalize-space()='Reorder']")).click();
            WebElement element = driver.findElement(By.xpath("//input[@title='Qty']"));
            element.click();
            element.sendKeys("2");
            driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();
            //debug purpose only
            Thread.sleep(2000);

            //5. Verify Grand Total is changed
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            // creates a new File object named f by combining a directory path (dirPath) with the name of the screenshot file
            File f = new File(dirPath + "/" + screenshot.getName());
            FileHandler.copy(screenshot, f);
            //debug purpose only
            Thread.sleep(2000);

            //6. Complete Billing & Shipping Information





        }catch (Exception e) {
            e.printStackTrace();
        }
        //Quit browser session
        driver.quit();
    }
}
