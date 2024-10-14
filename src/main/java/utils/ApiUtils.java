package utils;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.ContactListModel;

import static io.restassured.RestAssured.given;

public class ApiUtils implements TestHelper {

    public static boolean isContactExistsInApi(Contact contact){
        ContactListModel contactListModel = given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .when()
                .get(BASE_URL+GET_ALL_CONTACTS)
                .then().assertThat().statusCode(200)
                .extract().as(ContactListModel.class);

            return contactListModel.getContacts().stream()
                    .anyMatch(apiContact -> apiContact.getEmail().equals(contact.getEmail())
                            && apiContact.getPhone().equals(contact.getPhone()));

    }



}
