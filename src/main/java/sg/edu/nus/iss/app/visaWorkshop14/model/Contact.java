package sg.edu.nus.iss.app.visaWorkshop14.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Contact class represent the contact information
 * of the address book
 */
public class Contact implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String phoneNumber;
    private String id;

    public Contact(){
        this.id = generateId(8);
    }

    public Contact(String id, String name, String email, String phoneNumber){
        this.email = email;
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public Contact(String name, String email, String phoneNumber){
        this.email = email;
        this.name = name;
        this.id = generateId(8);
        this.phoneNumber = phoneNumber;
    }

    private synchronized String generateId(int numChars){
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while(strBuilder.length() < numChars){
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}