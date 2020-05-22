package sample;

import contacts.Contact;
import contacts.ContactBook;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private ObservableList<Contact> items = FXCollections.observableArrayList();

    @FXML
    private TextField FName;

    @FXML
    private TextField LName;

    @FXML
    private TextField TelNum;

    @FXML
    private TextField Street;

    @FXML
    private TextField BNum;

    @FXML
    private TextField LNum;

    @FXML
    private TextField PCode;

    @FXML
    private TextField GenDel;

    @FXML
    private Label FNameLab;

    @FXML
    private Label LNameLab;

    @FXML
    private Label TelNumLab;

    @FXML
    private Label StrLab;

    @FXML
    private Label BuildNumLab;

    @FXML
    private Label FlNumLab;

    @FXML
    private Label PCodeLab;

    @FXML
    private Label GenDelLab;

    @FXML
    private ListView<Contact> list = new ListView<>();

    @FXML
    private TextField searchField;

    @FXML
    private Button saveBut;

    @FXML
    private void newContact(ActionEvent event) {
        saveBut.setText("Add Contact");
        String fn = FName.getText();
        String ln = LName.getText();
        String tn = TelNum.getText();
        String st = Street.getText();
        String bn = BNum.getText();
        String locn = LNum.getText();
        String pc = PCode.getText();
        String gn = GenDel.getText();

        Contact contact = null;

        try {
            contact = ContactBook.getInstance().addContact(fn, ln, tn, st, bn, locn, pc, gn);
            ContactBook.getInstance().serializeContacts();
        } catch (Exception e) {
            System.out.println("Nie udało się zapisać kontaktu");
        }
        if (contact != null) {
            System.out.println("przechodzimy do new element");
            newElement(contact);
            FName.clear();
            LName.clear();
            TelNum.clear();
            Street.clear();
            BNum.clear();
            LNum.clear();
            PCode.clear();
            GenDel.clear();
        }
        list.requestFocus();
    }
    

    public void newElement(Contact contact){
        items.add(contact);
        list.requestFocus();
        System.out.println(list.getItems());
    }


    public void show(MouseEvent mouseEvent) {
        Contact contact = list.getSelectionModel().getSelectedItem();
        System.out.println("clicked on " + contact);
        showInfo(contact);
    }


    @FXML
    public void delete(ActionEvent event) {
        String name = FNameLab.getText() + ' ' + LNameLab.getText();
        Contact searched = ContactBook.getInstance().searchContact(name);
        ContactBook.getInstance().deleteContact(searched);
        deleteCon(searched);
    }

    public void showInfo(Contact contact) {
        System.out.println(contact.getFirstname());
        FNameLab.setText(contact.getFirstname());
        LNameLab.setText(contact.getLastname());
        TelNumLab.setText(contact.getTelNum());
        StrLab.setText(contact.getAddress().getStreet());
        BuildNumLab.setText(contact.getAddress().getBuildingNum());
        FlNumLab.setText(contact.getAddress().getFlatNum());
        PCodeLab.setText(contact.getAddress().getPostCode());
        GenDelLab.setText(contact.getAddress().getGeneralDelivery());
    }


    public void deleteCon (Contact contact){
        items.removeAll(contact);
        list.requestFocus();
        FNameLab.setText("");
        LNameLab.setText("");
        TelNumLab.setText("");
        StrLab.setText("");
        BuildNumLab.setText("");
        FlNumLab.setText("");
        PCodeLab.setText("");
        GenDelLab.setText("");
    }

    public void search(ActionEvent event) {
        try{
            String name = searchField.getText();
            Contact contact = ContactBook.getInstance().searchContact(name);
            showInfo(contact);
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Can't find this contact, try again.");

            alert.showAndWait();
        }

    }

    public void editContact(ActionEvent event){
        String name = FNameLab.getText() + " " + LNameLab.getText();
        Contact contact = ContactBook.getInstance().searchContact(name);
        ContactBook.getInstance().getContactList().remove(contact);
        System.out.println("tu jest?");
        FName.setText(contact.getFirstname());
        LName.setText(contact.getLastname());
        TelNum.setText(contact.getTelNum());
        Street.setText(contact.getAddress().getStreet());
        BNum.setText(contact.getAddress().getBuildingNum());
        LNum.setText(contact.getAddress().getFlatNum());
        PCode.setText(contact.getAddress().getPostCode());
        GenDel.setText(contact.getAddress().getGeneralDelivery());
        items.removeAll(contact);
        saveBut.setText("Update Contact");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.requestFocus();
        items.addAll(ContactBook.getInstance().getContactList());
        list.setItems(items);
        list.setCellFactory(param -> new ListCell<Contact>() {
            @Override
            protected void updateItem(Contact c, boolean empty){
                super.updateItem(c, empty);
                if(empty || c == null || c.getFirstname() == null){
                    setText("");
                }
                else{
                    setText(c.getFirstname() + " " + c.getLastname());
                    list.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) -> {
                    });
                }

            }
        });

        Platform.runLater(() -> list.requestFocus());
    }
}