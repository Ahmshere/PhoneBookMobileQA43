import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import io.appium.java_client.appmanagement.ApplicationState;
import models.RegistrationResult;
import org.testng.Assert;
import org.testng.annotations.Test;
import screenactions.ScreenUtil;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import javax.sound.midi.Soundbank;

public class RegistrationTests extends AppiumConfig {

    @Test
    public void registrationPositiveTest() {
        ContactListScreen contactListScreen = new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 5, 5, 3))
                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                .clickByRegistrationButton();
        Assert.assertTrue(contactListScreen.isContactListPresent());
    }

    @Test
    public void wrongEmailRegistration() {
        try {
            new SplashScreen(driver).switchToAuthenticationScreen()
                    .fillEmailField("treuyte")
                    .fillPasswordField("")
                    .clickByRegistrationButton();
        } catch (RuntimeException exception) {
            Assert.assertTrue(exception.getMessage().contains("at least"));
        }
    }

  /*  @Test
    public void regWithoutPassword() {
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        RegistrationResult result = authenticationScreen.fillEmailField(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 3, 7, 3))
                .fillPasswordField("123")
                .clickTestBaseScreen();
                //.clickByRegistrationButtonUsingRegistrationResult();
        if (!result.isSuccess()) {
            result.getErrorMessage().contains("least 8");
        } else {
            ContactListScreen contactListScreen = result.getContactListScreen();
        }
    }
*/
    @Test
    public void regWithoutPasswordUsingApplicationState() {
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        ApplicationState appState = driver.queryAppState("com.sheygam.contactapp");
        System.out.println("APP STATE: " + appState.toString());
        RegistrationResult result = authenticationScreen.fillEmailField(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 3, 7, 3))
                .fillPasswordField("")
                .clickByRegistrationButtonUsingRegistrationResult();
        System.out.println("RESULT MESSAGE: " + result.getErrorMessage());

        ScreenUtil screenUtil = new ScreenUtil(driver);
        screenUtil.takeScreenShot("RegistrationIssue");

        if (appState == ApplicationState.RUNNING_IN_FOREGROUND) {
            System.out.println("Running..");
        } else {
            System.out.println("Not....");
        }
        Assert.assertTrue(result.getErrorMessage().contains("Contact App has stopped"));
    }

}
