package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SplashScreen extends BaseScreen{
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/version_text']")
    MobileElement text;

    public SplashScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    public String getTextVersion(){
        return text.getText();
    }
    public AuthenticationScreen switchToAuthenticationScreen(){return new AuthenticationScreen(driver);}

    // Method to wait for SplashScreen to appear
    public void waitForSplashScreenToAppear() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(text));
    }
    // Method to wait for SplashScreen to disappear
    public void waitForSplashScreenToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(text));
    }

}
