package centralScotlandCollegeNamespace;

import java.io.Serializable;

public class Client implements Serializable {

    private String name;
    private String emailAddress;
    private String contactNumber;

    public Client()
    {
        name = "";
        emailAddress = "";
        contactNumber = "";
    }

    public Client(String name, String emailAddress, String contactNumber)
    {
        this.name = name;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
