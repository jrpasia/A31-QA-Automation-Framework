import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;


public class BaseTest {
    public static WebDriver driver = null;
    public static String url = null;
    public static WebDriverWait wait = null;
    public static FluentWait fluentWait = null;

    public static ThreadLocal<WebDriver> threadDriver;

    @BeforeSuite
    static void setupClass() {
//        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public static void launchBrowser(String BaseURL) throws MalformedURLException {
        driver = pickBrowser(System.getProperty("browser"));

        threadDriver = new ThreadLocal<>();
        threadDriver.set(driver);

        url = BaseURL;
        getDriver().get(url);
        wait = new WebDriverWait(LoginTests.getDriver(), Duration.ofSeconds(20));
    }
    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    @AfterMethod(enabled = true)
    public static void closeBrowser() {LoginTests.getDriver().quit();
        threadDriver.remove();
//    public static void closeBrowser() {
//        LoginTests.driver.quit();
    }

    private static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.50.41:4444";

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        return driver;
    }
//    public static void login(String email, String password) {
//        provideEmail(email);
//        providePassword(password);
//        clickSubmit();
//    }
//
//    public static void clickSubmit() {
//        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
//        submitButton.click();
//    }
//
//    public static void providePassword(String password) {
//        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
//        wait.until(ExpectedConditions.elementToBeClickable(passwordField));// use this when method only take WebElement
//
//        passwordField.clear();
//        passwordField.sendKeys(password);
//    }
//
//    public static void provideEmail(String email) {
//        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
//        emailField.clear();
//        emailField.sendKeys(email);
//    }
//
//    public static void clickSaveButton() {
//        WebElement saveButton = driver.findElement(By.cssSelector("button.btn-submit"));
//        saveButton.click();
//    }
//
//    public static void provideProfileName(String randomName) {
//        WebElement profileName = driver.findElement(By.cssSelector("[name='name']"));
//        profileName.clear();
//        profileName.sendKeys(randomName);
//    }
//
//    public static void provideCurrentPassword(String password) {
//        WebElement currentPassword = driver.findElement(By.cssSelector("[name='current_password']"));
//        currentPassword.clear();
//        currentPassword.sendKeys(password);
//    }
//
//    public static String generateRandomName() {
//        return UUID.randomUUID().toString().replace("-", "");//
//    }
//
//    public static void clickAvatarIcon() {
//        WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
//        avatarIcon.click();
//
//    }
//
//    @DataProvider(name="incorrectLoginProviders")
//    public static Object[][] getDataFromDataproviders() {
//
//        return new Object[][] {
//                {"invalid@email.com", "invalidPass"},
//                {"demo@mail.com", "invalid"},
//                {"", ""}
//        };
//    }

}

