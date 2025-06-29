package cdi;

import client.customer;
import ejb.user_beanLocal;
import entities.Tblcustomer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

@Named(value = "signup")
@SessionScoped
public class signup implements Serializable {

    @Inject
    private user_beanLocal ubl;

    private customer ebl;
    private Response rs;
    private Pbkdf2PasswordHashImpl pb;

    private String customerName;
    private String customerAddress;
    private String email;
    private String phno;
    private String password;
    private int role_id = 1;

    private Tblcustomer current;
    private Collection<Tblcustomer> cust;
    private GenericType<Collection<Tblcustomer>> gcust;

    public signup() {
        ebl = new customer();
        cust = new ArrayList<>();
        gcust = new GenericType<Collection<Tblcustomer>>() {};
    }

    @PostConstruct
    public void init() {
        pb = new Pbkdf2PasswordHashImpl();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "2048");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "32");
        parameters.put("Pbkdf2PasswordHash.KeySizeBytes", "32");
        pb.initialize(parameters);
    }

   public String addcust() {
    try {
        if (customerName == null || customerName.trim().length() < 4) {
            showMessage("Name must be at least 4 characters.");
            return null;
        }

        if (customerAddress == null || customerAddress.trim().length() < 4) {
            showMessage("Address must be at least 4 characters.");
            return null;
        }

        if (email == null || !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}$")) {
            showMessage("Invalid email format.");
            return null;
        }

        // Check for duplicate email
       Tblcustomer existing = ubl.getCustomersByEmail(email);
            if (existing != null) {
            showMessage("An account with this email already exists.");
            return null;
}

        if (phno == null || !phno.matches("\\d{10}")) {
            showMessage("Phone number must be 10 digits.");
            return null;
        }

        if (password == null || password.length() < 4) {
            showMessage("Password must be at least 4 characters.");
            return null;
        }

        String hashedPassword = pb.generate(password.toCharArray());
        ubl.addCustomer(customerName, customerAddress, email, phno, hashedPassword, role_id);

        return "login";
    } catch (Exception e) {
        showMessage("Error adding customer: " + e.getMessage());
        return null;
    }
}

    private void showMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    // Getters and Setters

    public customer getEbl() {
        return ebl;
    }

    public void setEbl(customer ebl) {
        this.ebl = ebl;
    }

    public Response getRs() {
        return rs;
    }

    public void setRs(Response rs) {
        this.rs = rs;
    }

    public Pbkdf2PasswordHashImpl getPb() {
        return pb;
    }

    public void setPb(Pbkdf2PasswordHashImpl pb) {
        this.pb = pb;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public Tblcustomer getCurrent() {
        return current;
    }

    public void setCurrent(Tblcustomer current) {
        this.current = current;
    }

    public Collection<Tblcustomer> getCust() {
        return cust;
    }

    public void setCust(Collection<Tblcustomer> cust) {
        this.cust = cust;
    }

    public GenericType<Collection<Tblcustomer>> getGcust() {
        return gcust;
    }

    public void setGcust(GenericType<Collection<Tblcustomer>> gcust) {
        this.gcust = gcust;
    }
}
