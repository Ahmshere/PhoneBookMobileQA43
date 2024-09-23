package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    MobileElement noContactsText;
    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    MobileElement addContactButton;
    public boolean isContactListPresent(){
        return isElementPresent(noContactsText, "No Contacts. Add One more!");
    }
}
