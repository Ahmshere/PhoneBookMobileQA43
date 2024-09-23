import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import models.RegistrationResult;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

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

    @Test
    public void regWithoutPassword(){
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        RegistrationResult result = authenticationScreen.fillEmailField(EmailGenerator.generateEmail(EmailGenerator.EmailType.VALID, 3,7,3))
                .fillPasswordField("123")
                .clickByRegistrationButtonUsingRegistrationResult();
        if (!result.isSuccess()){
            result.getErrorMessage().contains("least 8");
        }else {ContactListScreen contactListScreen = result.getContactListScreen();}
    }




}
