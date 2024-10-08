package mx.petcare.mascotas.petcareAPI.Model;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;
    private String lastname;
    private String secondlastname;
    private String email;
    private int phoneNumber;
    private String password;

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getSecondlastname() {
        return secondlastname;
    }
    public void setSecondlastname(String secondlastname) {
        this.secondlastname = secondlastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString () {
        return name + " :: " + 
               lastname + " :: " +
               secondlastname + " :: " +
               email + " :: " +
               phoneNumber + " :: " +
               password; 
    }   
}
