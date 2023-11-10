package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TestCase09 {
    @Test
    public static void testcase09() {
        // Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try {

            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");



//            2. Go to Mobile and add IPHONE to cart
            driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();

            driver.findElement(By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();
            
//            3. Enter Coupon Code
            WebElement coupoun = driver.findElement(By.xpath("//input[@id='coupon_code']"));
            coupoun.clear();
            coupoun.sendKeys("GURU50");
            driver.findElement(By.cssSelector("button[title='Apply'] span span")).click();

//            4. Verify the discount generated
            WebElement rate = driver
                    .findElement(By.cssSelector("tbody tr:nth-child(2) td:nth-child(2) span:nth-child(1)"));
            WebElement pretotal = driver.findElement(By.cssSelector(
                    "body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(2) > span:nth-child(1)"));
//            AssertJUnit.assertEquals(Float.parseFloat(
////                    rate.getText().substring(2)
////            )/Float.parseFloat(
////                    pretotal.getText().substring(1)
////            ), Float.parseFloat("0.05"));
            WebElement grandtotal = driver.findElement(By.cssSelector("strong span[class='price']"));
            Assert.assertNotEquals(grandtotal.getText(),pretotal.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
