package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class TestCase08 {
    public static void testcase08(){
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

            //3. Login in application using previously created credential
            driver.findElement(By.id("email")).sendKeys("07linhnguyen@gmail.com");
            driver.findElement(By.id("pass")).sendKeys("123456");
            driver.findElement(By.id("send2")).click();

            //4. Click on 'REORDER' link , change QTY & click Update
            driver.findElement(By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']")).click();
            WebElement element = driver.findElement(By.xpath("//input[@title='Qty']"));
            element.click();
            element.clear();
            element.sendKeys("2");
            driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();
            //debug purpose only
            Thread.sleep(1000);

            //5. Verify Grand Total is changed
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            // creates a new File object named f by combining a directory path (dirPath) with the name of the screenshot file
            File f = new File(dirPath + "/" + screenshot.getName());
            FileHandler.copy(screenshot, f);

            //6. Complete Billing & Shipping Information
            driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]")).click();
            driver.findElement(By.xpath("//button[@onclick='billing.save()']")).click();
            Thread.sleep(2000);

//            driver.findElement(By.xpath("//button[@onclick='shipping.save()']//span//span[contains(text(),'Continue')]")).click();
//            Thread.sleep(2000);

            driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']")).click();
            Thread.sleep(2000);

            element = driver.findElement(By.xpath("//input[@id='p_method_checkmo']"));
            element.click();
            Thread.sleep(1000);
            element = driver.findElement(By.xpath("//button[@onclick='payment.save()']"));
            element.click();
            Thread.sleep(1000);

            element = driver.findElement(By.xpath("//button[@title='Place Order']"));
            element.click();
            Thread.sleep(2000);

            //7. Verify order is generated and note the order number
            TakesScreenshot takesScreenshot2 = (TakesScreenshot) driver;
            File screenshot2 = takesScreenshot2.getScreenshotAs(OutputType.FILE);
            // creates a new File object named f by combining a directory path (dirPath) with the name of the screenshot file
            File f2 = new File(dirPath + "/" + screenshot2.getName());
            FileHandler.copy(screenshot2, f2);

        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
