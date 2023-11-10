package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement email() {
        return driver.findElement(By.id("email"));
    }

    private WebElement password() {
        return driver.findElement(By.id("pass"));
    }

    private WebElement loginButton() {
        return driver.findElement(By.id("send2"));
    }

    public void inputEmail(String emailText) {
        email().click();
        email().sendKeys(emailText);
    }

    public void inputPassword(String passwordText) {
        password().clear();
        password().sendKeys(passwordText);
    }

    public void clickLoginBtn() {
        loginButton().click();
    }
}
