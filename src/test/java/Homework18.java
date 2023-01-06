import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class Homework18 extends BaseTest{

    @Test
    public static void playSongTest() throws InterruptedException {


        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Actions act = new Actions(driver);

        String url = "https://bbb.testpro.io/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        //Valid Email Login
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.clear();
        emailField.sendKeys("jrpasia@gmail.com");

        //Valid Password Login
        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        passwordField.clear();
        passwordField.sendKeys("B3n@iah2013");

        //Login Button
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        Thread.sleep(3000);
        String homepageURL = "https://bbb.testpro.io/#!/home";
        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);

        //Click All Songs Link
        WebElement allSongsPage = driver.findElement(By.cssSelector("[class='songs']"));
        allSongsPage.click();
        String allSongsURL = "https://bbb.testpro.io/#!/songs";
        Assert.assertEquals(driver.getCurrentUrl(), allSongsURL);

        //Double Click "Ketsa-Beautiful"
        WebElement doubleClickSelectedSong = driver.findElement(By.xpath("//*[@id=\"songsWrapper\"]/div/div/div[1]/table/tr[7]/td[2]"));
        act.doubleClick(doubleClickSelectedSong).perform();
        Thread.sleep(5000);

        //Right-click to pause
        act.contextClick(doubleClickSelectedSong).build().perform();
        WebElement selectPause = driver.findElement(By.xpath("//*[@id=\"app\"]/nav/ul/li[1]/span"));
        selectPause.click();
        Thread.sleep(5000);

        //Right-click to resume playing
        act.contextClick(doubleClickSelectedSong).build().perform();
        WebElement selectPlay = driver.findElement(By.xpath("//*[@id=\"app\"]/nav/ul/li[1]/span"));
        selectPlay.click();


//        WebElement pauseButton = driver.findElement(By.cssSelector("span.pause>i"));
//        Thread.sleep(1000);
//        pauseButton.click();

        //Verify equalizer bars is displayed
        WebElement equalizerBars = driver.findElement(By.cssSelector("[alt='Sound bars']"));
        Assert.assertTrue(equalizerBars.isDisplayed());
        Thread.sleep(5000);
        driver.quit();

    }
}
