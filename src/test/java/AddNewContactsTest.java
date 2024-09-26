import config.AppiumConfig;
import helpers.ContactGenerator;
import models.Contact;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactsTest extends AppiumConfig {

    @Test
    public void addNewContactPositive() {
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen = new ContactListScreen();

    }
}
