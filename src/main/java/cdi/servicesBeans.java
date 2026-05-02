/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import client.services;
import ejb.admin_beansLocal;
import ejb.user_bean;
import entities.Tblemployee;
import entities.Tblpayment;
import entities.Tblservice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import mail.feedBackMail;
import mail.mail;
import javax.faces.application.FacesMessage;

/**
 *
 * @author LENOVO
 */
@Named(value = "servicesBeans")
@RequestScoped
public class servicesBeans implements Serializable {

    @EJB
    private admin_beansLocal admin_beans;
//private Collection<Integer> subid = new ArrayList<>();
    services s;
    Response rs;
//    private List<Integer> serviceId; // List of selected service IDs
//    private Collection<Tblservice> allServices;

    Collection<Tblservice> service = new ArrayList<>();
    GenericType<Collection<Tblservice>> gservice;
    private int services_id;
    Collection<Tblemployee> emp;
    private Tblservice se;
    private String userEmail;
    @Inject
    private LoginMB loginMB;
    @Inject
 

    @PostConstruct
    public void init() {
        this.userEmail = loginMB.getC().getEmail();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        String updateString = facesContext.getExternalContext().getRequestParameterMap().get("servicesId");

        if (updateString != null) {
            try {
                this.services_id = Integer.parseInt(updateString);
                loadOrderDetails(services_id);
                System.out.println("Username:" + this.se.getServicesId());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadOrderDetails(int id) {
//        System.out.println("Posts count:"+this.posts.size());
        this.se = admin_beans.getAllServiceById(id);
    }

    public Tblservice getSe() {
        return se;
    }

//    public List<Integer> getServiceId() {
//        return serviceId;
//    }
//    public void setServiceId(List<Integer> serviceId) {
//        this.serviceId = serviceId;
//    }
    public void setSe(Tblservice se) {
        this.se = se;
    }

    public Response getRs() {
        return rs;
    }

    public void setRs(Response rs) {
        this.rs = rs;
    }

    public Collection<Tblservice> getService() {
        return service;
    }

    public void setService(Collection<Tblservice> service) {
        this.service = service;
    }

    public GenericType<Collection<Tblservice>> getGservice() {
        return gservice;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setGservice(GenericType<Collection<Tblservice>> gservice) {
        this.gservice = gservice;
    }

    public int getServices_id() {
        return services_id;
    }

    public void setServices_id(int services_id) {
        this.services_id = services_id;
    }

    public Collection<Tblemployee> getEmp() {
        return emp;
    }

    public void setEmp(Collection<Tblemployee> emp) {
        this.emp = emp;
    }

    private String service_type;
    private int charge;
    private int salary;

    private String emp_address;
    private String emp_phno;
    private String emp_name;

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public String getEmp_phno() {
        return emp_phno;
    }

    public void setEmp_phno(String emp_phno) {
        this.emp_phno = emp_phno;
    }

    public services getS() {
        return s;
    }

    public void setS(services s) {
        this.s = s;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public void addservice() {
        s.addservice(service_type, String.valueOf(service_type));
    }

    public Collection<Tblservice> getAllServices() {
        Collection<Tblservice> ser = admin_beans.getAllServices();
        return ser;
    }

//    public void setAllServices(Collection<Tblservice> allServices) {
//        this.allServices = allServices;
//    }
//    public Collection<Tblservice> getAllServices() {
////        rs = s.getAllServices(Response.class);
////        service = rs.readEntity(gservice);
////       return service;
//
//        return admin_beans.getAllServices();
//    }
    public Tblservice getAllServiceById(int services_id) {
        return admin_beans.getAllServiceById(services_id);

    }

   
    public void deleteService(int services_id) {
        admin_beans.deleteservice(services_id);
    }

    public void addservices() {
        admin_beans.addservice(service_type, charge);
    }

    public String addemp() {
        try {
            // Validate salary
            if (salary <= 0) {
                FacesContext.getCurrentInstance().addMessage("salary",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Salary must be greater than 0", null));
                return null;
            }

            // Validate phone number format (10 digits)
            if (emp_phno == null || !emp_phno.matches("\\d{10}")) {
                FacesContext.getCurrentInstance().addMessage("emp_phno",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Phone number must be 10 digits", null));
                return null;
            }

            // Validate name (no numbers or special characters)
            if (emp_name == null || !emp_name.matches("^[a-zA-Z\\s]+$")) {
                FacesContext.getCurrentInstance().addMessage("emp_name",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Name should contain only letters", null));
                return null;
            }

            // Validate address is not empty
            if (emp_address == null || emp_address.trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage("emp_address",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Address cannot be empty", null));
                return null;
            }

            // If all validations pass, add the employee
            // Convert salary to String since that's what the method expects
            admin_beans.addEmployee(emp_name, services_id, salary, emp_address, emp_phno);
            
            // Add success message
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Employee added successfully!", null));

            // Redirect to employee display page
            return "employeeDisplay?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error adding employee: " + e.getMessage(), null));
            return null;
        }
    }

    public Collection<Tblemployee> getAllEmployee() {
        Collection<Tblemployee> e = admin_beans.getAllEmployees();
        return e;
    }

    public void deleteEmp(int emp_id) {
        admin_beans.removeEmployee(emp_id);
    }

    public String updateService() {
        admin_beans.updateservice(this.se.getServicesId(), service_type, charge);
        return "serviceDisplay?faces-redirect=true";
    }

    /**
     * Creates a new instance of servicesBeans
     */
    public servicesBeans() {
        se = new Tblservice();
        s = new services();
        service = new ArrayList<>();
        gservice = new GenericType<Collection<Tblservice>>() {
        };
        emp = new ArrayList<>();
    }

    private String message;
    private String name;
    private String email;
    private String subject;
    
    @Inject
    private feedBackMail feedBackMail;
    
    // Getters and setters for all properties
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String sendFeedBackMail() {
        try {
            // Create a more detailed email message
            String emailBody = "Name: " + name + "\n" +
                             "Email: " + email + "\n" +
                             "Subject: " + subject + "\n" +
                             "Message: " + message;
                             
            feedBackMail.sendEmail(email, new Date());
            
            // Clear the form after successful send
            this.message = "";
            this.name = "";
            this.email = "";
            this.subject = "";
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Your message has been sent."));
            return null;
        } catch (Exception e) {
            e.printStackTrace(); // This will help with debugging
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to send message: " + e.getMessage()));
            return null;
        }
    }

}
