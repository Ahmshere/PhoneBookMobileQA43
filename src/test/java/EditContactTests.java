import config.AppiumConfig;
import enums.ContactField;
import enums.Directions;
import helpers.ContactGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.EditContactScreen;
import screens.SplashScreen;

public class EditContactTests extends AppiumConfig implements TestHelper {

    @Test
    public void editContactTestPositive() {
        String modifiedFieldValue = "mynewmail@test.com";
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser", XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass", XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen = new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
        Assert.assertTrue(contactListScreen
                .editMyContact(0)
                .editField(ContactField.EMAIL, modifiedFieldValue)
                .updateEditContact()
                .isContactModified(modifiedFieldValue, 0));
    }

    @Test
    public void addContactAndCheckTheList() {
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser", XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass", XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen = new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
        contactListScreen.isContactAddedWithScroll(contact);
    }

    @Test
    public void editOrRemoveContact()  {
        Contact contact = ContactGenerator.createValidContact();
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField(PropertiesReaderXML.getProperties("myuser", XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties("mypass", XML_DATA_FILE))
                .clickLoginButtonUsingRegistrationResult();
        ContactListScreen contactListScreen = new ContactListScreen(driver)
                .openNewForm()
                .fillNewContactForm(contact)
                .createContact();
       contactListScreen.swipeAndActOnContact(contact, Directions.LEFT);
        //editContactScreen.editField(ContactField.EMAIL, "modifiedFieldV@mail.com");

    }

}
