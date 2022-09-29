package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicTest {
    static WebDriver driver;
    @BeforeTest
    @Parameters("browser")
    public void setUp (@Optional("chrome") String browser) throws MalformedURLException
    {
        if(browser.equalsIgnoreCase("chrome"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "chrome");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }

        else if(browser.equalsIgnoreCase("firefox"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "firefox");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }

    }
    public BasicTest GoTo(String url){
        driver.get(url);
        driver.manage().window().maximize();
        System.out.println(1);
        return this;
    }
    public BasicTest WaitUntil(String name){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(name)));
        return this;
    }
    public BasicTest MoveTo(String name){
        Actions action = new Actions(driver);
        WebElement webElement = driver.findElement(By.linkText("Grim Dark 30c"));
        action.moveToElement(webElement).build().perform();
        System.out.println(2);
        return this;
    }
    public BasicTest Click(String name){
        WebElement webElement = driver.findElement(By.linkText(name));
        webElement.click();
        System.out.println(3);
        return this;
    }
    public BasicTest SignIn() {
        WebElement webElement = driver.findElement(By.linkText("Sign in"));
        webElement.click();
        System.out.println(4);
        webElement = driver.findElement(By.linkText("Login"));
        webElement.click();
        System.out.println(5);
        return this;
    }
    public BasicTest LogIn(){
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"input-email\"]"));
        webElement.sendKeys("EMAIL");
        webElement = driver.findElement(By.xpath("//*[@id=\"input-password\"]"));
        webElement.sendKeys("PASSWORD");
        webElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"));
        System.out.println(6);
        webElement.click();
        System.out.println(7);
        webElement = driver.findElement(By.xpath("/html/body/div[2]/div[1]/i[@class=\"fa fa-exclamation-circle\"]"));
        if (webElement != null){
            System.out.println("Incorrect Email or Password!");
            System.out.println("Or the number of retries has been exceeded. Try again in 1 hour.");
        }
        return this;
    }

    @Test
    public void FirstTest() {
        BasicTest SomePage = new BasicTest();
        SomePage.GoTo("https://tortuga-gamestable.top/")
                .WaitUntil("Grim Dark 30c")
                .MoveTo("Grim Dark 30c")
                .Click("Betrayers (32)");

    }
    @Test
    public void SecondTest() {
        BasicTest NewPage = new BasicTest();
        NewPage.GoTo("https://tortuga-gamestable.top/").SignIn().LogIn();
    }
}
