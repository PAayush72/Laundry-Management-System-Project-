/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import client.services;
import ejb.admin_beansLocal;
import entities.Tblservice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author LENOVO
 */
@Named(value = "serviceBean")
@RequestScoped
public class serviceBean {

    @EJB
    private admin_beansLocal admin_beans;
//private Collection<Integer> subid = new ArrayList<>();
    services s;
    Response rs;
     private List<Integer> serviceId; // List of selected service IDs
    private Collection<Tblservice> allServices; 
    Collection<Tblservice> service = new ArrayList<>();
    GenericType<Collection<Tblservice>> gservice;
    private int services_id;
    private String message; // Add this property for success/error messages

    @PostConstruct
    public void init() {
        // Assuming userBean.getAllServices() fetches the list of all available services
        allServices = admin_beans.getAllServices();
        serviceId = new ArrayList<>();
    }

    public List<Integer> getServiceId() {
        return serviceId;
    }

    public void setServiceId(List<Integer> serviceId) {
        this.serviceId = serviceId;
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

    public void setGservice(GenericType<Collection<Tblservice>> gservice) {
        this.gservice = gservice;
    }

    public int getServices_id() {
        return services_id;
    }

    public void setServices_id(int services_id) {
        this.services_id = services_id;
    }

    private String service_type;
    private int charge;

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
        try {
            // Validate the charge
            if (charge <= 0) {
                FacesContext.getCurrentInstance().addMessage("charge",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Service charge must be greater than 0", null));
                return;
            }
            this.charge = charge;
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage("charge",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Please enter a valid number", null));
        }
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public String addservice() {
        try {
            // Validate before adding
            if (service_type == null || service_type.trim().isEmpty()) {
                message = "Error: Service type is required";
                return null;
            }
            
            if (charge <= 0) {
                message = "Error: Service charge must be greater than 0";
                return null;
            }
            
            s.addservice(service_type, String.valueOf(charge));
            message = "Service added successfully!";
            return "serviceDisplay.jsf?faces-redirect=true";
        } catch (Exception e) {
            message = "Error adding service: " + e.getMessage();
            return null;
        }
    }

    public Collection<Tblservice> getAllServices() {
        return allServices;
    }

    public void setAllServices(Collection<Tblservice> allServices) {
        this.allServices = allServices;
    }

    public Collection<Tblservice> getAllServiceById(int services_id) {
        rs = s.getAllServiceById(Response.class, String.valueOf(services_id));
        service = rs.readEntity(gservice);
        return service;
    }

    /**
     * Creates a new instance of servicesBeans
     */
    public serviceBean() {

        s = new services();
        service = new ArrayList<>();
        gservice = new GenericType<Collection<Tblservice>>() {
        };
    }

}
