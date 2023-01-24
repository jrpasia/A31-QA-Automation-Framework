package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends pages.BasePage {

    //Locators
    @FindBy(css = "[class='home active']")
    private WebElement homeButton;
    @FindBy(css = "[class='queue']")
    private WebElement currentQue;
    @FindBy(css = "[class='songs']")
    private WebElement allSongsButton;
    private By userAvatarIcon = By.cssSelector("img.avatar");

    public HomePage( WebDriver givenDriver) {
        super(givenDriver);
    }
    public WebElement getUserAvatar () {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userAvatarIcon));
    }
    public HomePage clickHomeButton() {
        homeButton.click();
        return this;
    }
    public HomePage clickCurrentQueBtn() {
        currentQue.click();
        return this;
    }
    public HomePage clickAllSongsBtn() {
        allSongsButton.click();
        return this;
    }
}
