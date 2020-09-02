package Pojo;

public class Contact {

    private int contactId;
    private String phone;
    private String emailAddress;
    private String premanentAddress;


    public Contact() {
    }
    public Contact(int contactId, String phone, String emailAddress, String premanentAddress) {
        this.contactId = contactId;
        this.phone = phone;
        this.emailAddress = emailAddress;
        this.premanentAddress = premanentAddress;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPremanentAddress() {
        return premanentAddress;
    }

    public void setPremanentAddress(String premanentAddress) {
        this.premanentAddress = premanentAddress;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", phone='" + phone + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", premanentAddress='" + premanentAddress + '\'' +
                '}';
    }
}
