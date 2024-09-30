package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    MobileElement noContactsText;
    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    MobileElement addContactButton;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement title;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> rowName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> rowPhone;

    public boolean isContactListPresent() {
        return isElementPresent(title, "Contact list");
    }

    public AddNewContactScreen openNewForm() {
        addContactButton.click();
        return new AddNewContactScreen(driver);
    }

    public boolean isContactAdded(Contact contact) {
        boolean nameExistance = textContains(rowName, contact.getName());
        boolean phoneExistance = textContains(rowPhone, contact.getPhone());
        return nameExistance && phoneExistance;
    }

    public boolean textContains(List<MobileElement> list, String text) {
        for (MobileElement mobileElement : list) {
            if (mobileElement.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }


}
