package BAITAP;

import org.testng.annotations.Test;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;

public class TestCase04 {
    @Test
    public static void testcase04() {
        String dirPath = "screenshots";
        File dir = new File(dirPath);
        //check dir exists
        if (!dir.exists()) {
            dir.mkdir();
        }

        WebDriver driver = driverFactory.getChromeDriver();
        //casting the driver object to the TakesScreenshot interface
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        try {
//        Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

//        2. Click on  MOBILE  menu
            driver.findElement(By.linkText("MOBILE")).click();

//      3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)
            driver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
            driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();

//      4. Click on �COMPARE� button. A popup window opens
            driver.findElement(By.xpath("//button[@title='Compare']")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            String heading =  driver.findElement(By.xpath("//h1[normalize-space(🙁'Compare Products']")).getText();
            System.out.println("ErrorMsg: "+ heading);
            if(!heading.isEmpty()){

                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                File f = new File(dirPath + "/" + screenshot.getName());
                FileHandler.copy(screenshot, f);

//                5. Verify the error message
                AssertJUnit.assertEquals("COMPARE PRODUCTS", heading);
                driver.close();
            }

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
