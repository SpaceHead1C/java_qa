package ru.nd.addressbook.tests;

import org.testng.annotations.Test;
import ru.nd.addressbook.model.ContactData;
import ru.nd.addressbook.model.Groups;

import java.io.File;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testGroupCreation() throws Exception {
        Groups groups = app.db().groups();
        File photo = new File("src\\test\\resources\\stru.png");
        ContactData newContact = new ContactData().withName("test_name").withLastname("test_surname").withPhoto(photo)
                .inGroup(groups.iterator().next());
        app.goTo().gotoHomePage();
        app.contact().initContactCreation();

        app.contact().fillContactForm(newContact, true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
    }
}
