package BAITAP;
import org.testng.annotations.Test;
import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class TestCase05 {
    @Test
    public static void testcase05() {
        WebDriver driver = driverFactory.getChromeDriver();

        String dirPath = "screenshots";
        File dir = new File(dirPath);
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

            //3. Click Create an Account link and fill New User information excluding the registered Email ID.
            driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.inputFirstname("Tran");
            registerPage.inputMiddlename("Van");
            registerPage.inputLastname("Tho");
            registerPage.inputEmail("05linhnguyen@gmail.com");
            registerPage.inputPassword("123456");
            registerPage.inputConfirmPassword("123456");
            registerPage.clickOnRegisterBtn();
            //debug purpose only

            //5. Verify Registration is done. Expected account registration done.
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File f = new File(dirPath + "/" + screenshot.getName());
            FileHandler.copy(screenshot, f);

            //6. Go to TV menu
            driver.findElement(By.xpath("//a[normalize-space(🙁'TV']")).click();

            //7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();

            //8. Click SHARE WISHLIST
            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();

            //9. In next page enter Email and a message and click SHARE WISHLIST
            WebElement email = driver.findElement(By.xpath("//textarea[@id='email_address']"));
            email.click();
            email.sendKeys("lili123@gmail.com");

            WebElement msg =  driver.findElement(By.xpath("//textarea[@id='message']"));
            msg.click();
            msg.sendKeys("so good!!");

            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();


            //10.Check wishlist is shared. Expected wishlist shared successfully.
            TakesScreenshot takesScreenshot2 = (TakesScreenshot) driver;

            File screenshot2 = takesScreenshot2.getScreenshotAs(OutputType.FILE);
            File f2 = new File(dirPath + "/" + screenshot2.getName());
            FileHandler.copy(screenshot2, f2);

        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
