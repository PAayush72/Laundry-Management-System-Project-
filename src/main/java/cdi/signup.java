/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import client.customer;
import ejb.user_beanLocal;
import entities.Tblcustomer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author LENOVO
 */
@Named(value = "signup")
@SessionScoped
public class signup implements Serializable {

    @Inject
    private user_beanLocal ubl;

    customer ebl;
    Response rs;
//    Pbk d;
    Pbkdf2PasswordHashImpl pb;
    private String customerName;
    private String customerAddress;
    private String email;
    private String phno;
    private String password;
    private int role_id = 1;

    Tblcustomer current;
    Collection<Tblcustomer> cust;
    GenericType<Collection<Tblcustomer>> gcust;

    /**
     * Creates a new instance of signup
     */
    public signup() {
        ebl = new customer();
        cust = new ArrayList<>();
        gcust = new GenericType<Collection<Tblcustomer>>() {
        };
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

    public String addcust() {
        try {

//            System.out.println("Method triggerd:"+email);
//            Tblcustomer c = ubl.getCustomersByEmail(email);
//            System.out.println("Customer email:"+c);
//            if (c != null) {
//                System.out.println("If triggerd");
//                FacesMessage emailExistError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username Already Exists", null);
//                FacesContext.getCurrentInstance().addMessage(null, emailExistError);
//                return null;
//            }
//            System.out.println("Else triggerd");s
            String hashedPassword = pb.generate(password.toCharArray());
//            ebl.addCustomer(customerName, customerAddress, email, phno, hashedPassword, String.valueOf(role_id));
            ubl.addCustomer(customerName, customerAddress, email, phno, hashedPassword, role_id);
            return "login";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error adding customer", e.getMessage()));
            return null;
        }

    }

}
