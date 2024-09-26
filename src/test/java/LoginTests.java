import config.AppiumConfig;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.RegistrationResult;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig implements TestHelper {

    @Test
    public void loginPositive() {
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        ContactListScreen contactListScreen = authenticationScreen.fillEmailField(PropertiesReaderXML.getProperties("myuser", XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass", XML_DATA_FILE))
                .clickByLoginButton();
        Assert.assertTrue(contactListScreen.isContactListPresent());

    }

    @Test
    public void loginNegative_001() {
        try {
            new SplashScreen(driver).switchToAuthenticationScreen()
                    .fillEmailField("dfgytw")
                    .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                    .clickByLoginButton();
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Login or Password incorrect"));
        }
    }

    @Test
    public void loginNegativeRegistrationResult() {
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        RegistrationResult result = authenticationScreen.fillEmailField("sgfdsfds@fgb.co")
                .fillPasswordField("")
                .clickLoginButtonUsingRegistrationResult();
        System.out.println("ERROR MESSAGE: " + result.getErrorMessage());
        Assert.assertTrue(result.getErrorMessage().contains("Login or Password incorrect"));
    }


}
