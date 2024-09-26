package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.RegistrationResult;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AuthenticationScreen extends BaseScreen {
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement titleText;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement inputEmailField;
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    MobileElement inputPasswordField;
    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    MobileElement registrationButton;
    @FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    MobileElement loginButton;
    @FindBy(id = "android:id/message")
    MobileElement alertMessage;

    public MobileElement getAlertMessage() {
        return alertMessage;
    }

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    public AuthenticationScreen fillEmailField(String email){
        waitForAnElement(inputEmailField);
        inputEmailField.sendKeys(email);
        return this;
    }
    public AuthenticationScreen fillPasswordField(String password){
        inputPasswordField.sendKeys(password);
        return this;
    }

    public <T extends BaseScreen> T clickByRegistrationButton(){
        registrationButton.click();
        List<MobileElement> list = driver.findElements(By.id("android:id/alertTitle"));
        if(list.size()>0){
            setErrorMsg(alertMessage.getText());
            System.out.println("Alert text: "+alertMessage.getText());
            driver.findElement(By.id("android:id/button1")).click();
            return (T)new AuthenticationScreen(driver);
        }
        return (T) new ContactListScreen(driver);
    }

    public RegistrationResult clickByRegistrationButtonUsingRegistrationResult() {
        registrationButton.click();
        String msg = null;
        List<MobileElement> errorList = driver.findElements(By.id("android:id/alertTitle"));
        if(errorList.size()>0){
            List<MobileElement> errorMessage = driver.findElements(By.id("android:id/message"));
            if(errorMessage.size()>0){
                msg = errorMessage.get(0).getText();
            }else {msg=errorList.get(0).getText();}
            return new RegistrationResult(false, msg, null);
        }else {
            return new RegistrationResult(true, null, new ContactListScreen(driver));
        }
    }

    public RegistrationResult clickLoginButtonUsingRegistrationResult(){
        return clickLoginButtonUsingRegistrationResult(loginButton);
    }
    public RegistrationResult clickRegistrationButtonUsingRegistrationResult(){
        return clickLoginButtonUsingRegistrationResult(registrationButton);
    }
    public RegistrationResult clickLoginButtonUsingRegistrationResult(MobileElement button) {
        button.click();
        String msg = null;
        List<MobileElement> errorList = driver.findElements(By.id("android:id/alertTitle"));
        if(errorList.size()>0){
            List<MobileElement> errorMessage = driver.findElements(By.id("android:id/message"));
            if(errorMessage.size()>0){
                msg = errorMessage.get(0).getText();
            }else {msg=errorList.get(0).getText();}
            return new RegistrationResult(false, msg, null);
        }else {
            return new RegistrationResult(true, null, new ContactListScreen(driver));
        }
    }


    public <T extends BaseScreen> T clickByLoginButton() {
        loginButton.click();
        List<MobileElement> list = driver.findElements(By.id("android:id/alertTitle"));
        if (list.size()>0){
            return (T)new AuthenticationScreen(driver);
        }else {
            return (T)new ContactListScreen(driver);
        }



    }
}
