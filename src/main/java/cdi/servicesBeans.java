/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import client.services;
import ejb.admin_beansLocal;
import entities.Tblservice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author LENOVO
 */
@Named(value = "servicesBeans")
@ViewScoped
public class servicesBeans implements Serializable{

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

    @PostConstruct
    public void init() {
        // Assuming userBean.getAllServices() fetches the list of all available services
//        allServices = admin_beans.getAllServices();
//        serviceId = new ArrayList<>();
    }

//    public List<Integer> getServiceId() {
//        return serviceId;
//    }

//    public void setServiceId(List<Integer> serviceId) {
//        this.serviceId = serviceId;
//    }

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

    /**
     * Creates a new instance of servicesBeans
     */
    public servicesBeans() {

        s = new services();
        service = new ArrayList<>();
        gservice = new GenericType<Collection<Tblservice>>() {
        };
    }

}
