package contacts;

public class Contact {
    private String firstname;
    private String lastname;
    private String telNum;
    private Address address;

    public Contact(String fn, String ln, String  tn, Address addr) {
        firstname = fn;
        lastname = ln;
        telNum = tn;
        address = addr;

    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}