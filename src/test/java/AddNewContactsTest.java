import config.AppiumConfig;
import enums.ContactField;
import helpers.ContactGenerator;
import models.Contact;
import models.RegistrationResult;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.io.IOException;

public class AddNewContactsTest extends AppiumConfig {

    @Test
    public void addNewContactPositive() throws IOException, InterruptedException {
        swicthDataAndWiFi(false);
        Thread.sleep(7000);
        Contact contact = ContactGenerator.createValidContact();


        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen = new ContactListScreen(driver);
        contactListScreen
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
        Assert.assertTrue(contactListScreen.isContactAdded(contact));
    }

    @Test
    public void addNewContactNegative() {
        Contact contact = ContactGenerator.createInvalidContact("asdqwe", ContactField.EMAIL);
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButtonUsingRegistrationResult();
        AddNewContactScreen addNewContactScreen = new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
        Assert.assertTrue(addNewContactScreen.isTheErrorPresent("email"));
        // TASK
    }

    @Test
    public void addNewContactApproach2() throws InterruptedException {
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButtonUsingRegistrationResult();
        RegistrationResult result = new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .clickByCreate();
        ContactListScreen contactListScreen = result.getContactListScreen();
        contactListScreen.clickOnContact(contact.getName(), contact.getPhone());
        Thread.sleep(3000);

    }

    @Test
    public void addMultipleContacts(){
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButtonUsingRegistrationResult();
        new ContactListScreen(driver).addContacts(5);
    }


}
