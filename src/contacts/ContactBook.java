package contacts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactBook {
    private static final ContactBook instance = new ContactBook();
    private List<Contact> contactList;

    public ContactBook() {
        setContactList(new ArrayList<>());
    }

    public void serializeContacts() {
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Contact>>(){}.getType();
        String jsonString = gson.toJson(getContactList(), listOfTestObject);
        try {
            FileWriter writer = new FileWriter(new File("contacts.json").getAbsoluteFile(), false);
            writer.write(jsonString);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeContacts() {
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Contact>>(){}.getType();
        try {
            String readString = new String(Files.readAllBytes(Paths.get("contacts.json")));
            setContactList(gson.fromJson(readString, listOfTestObject));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Contact addContact(String fn, String ln, String  tn, String st, String bn, String locn, String pc, String gn) throws IOException {
        Contact contact = new Contact(fn, ln, tn, new Address(st, bn, locn, pc, gn));
        getContactList().add(contact);
        return contact;
    }

    public void deleteContact(Contact contact){
           contactList.remove(contact);
    }

    public Contact searchContact(String tofind){
        Contact searched = null;
        try {
            searched = ContactBook.getInstance().getContactList().stream().filter( x -> (tofind).contains(x.getFirstname()) && (tofind).contains(x.getLastname())).collect(Collectors.toList()).get(0);
            System.out.println(searched.getFirstname() + " " + searched.getLastname());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return searched;
    }

    public static ContactBook getInstance() {
        return instance;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
