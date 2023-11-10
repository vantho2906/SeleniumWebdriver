package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;

public class TestCase07 {
    @Test
    public static void testcase07(){
        WebDriver driver = driverFactory.getChromeDriver();
        
        try{
//          Step 1 Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            
//          Step 2 Click on My Account link
            driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']")).click();
            driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']")).click();

//          Step 3 Login in application using previously created credential
            driver.findElement(By.id("email")).sendKeys("quangtnse171198@gmail.com");
            driver.findElement(By.id("pass")).sendKeys("123456");
            driver.findElement(By.id("send2")).click();

//          Step 4 Click on 'My Orders'
            driver.findElement(By.xpath("//a[normalize-space()='My Orders']")).click();
            
//          Step  5 Click on 'View Order'
            driver.findElement(By.xpath("//a[normalize-space()='View Order']")).click();

            driver.findElement(By.xpath("//a[@class='link-print']")).click();

            String dirPath = "screenshots";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File f = new File(dirPath + "/" + screenshot.getName());
            FileHandler.copy(screenshot, f);

        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
