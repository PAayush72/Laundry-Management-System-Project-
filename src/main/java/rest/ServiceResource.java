/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.admin_beansLocal;
import entities.Tblemployee;
import entities.Tblservice;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author LENOVO
 */
@Path("Service")
@RequestScoped
public class ServiceResource {

    @EJB
    admin_beansLocal u;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceResource
     */
    public ServiceResource() {
    }

    /**
     * Retrieves representation of an instance of rest.ServiceResource
     *
     * @return an instance of java.lang.String
     */
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @POST
    @Path("/addservice/{service_type}/{charge}")
    public void addservice(@PathParam("service_type") String service_type,
            @PathParam("charge") int charge) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.addservice(service_type, charge);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateservice/{services_id}/{service_type}/{charge}")
    public void updateservice(@PathParam("services_id") int services_id,
            @PathParam("service_type") String service_type,
            @PathParam("charge") int charge) {
        u.updateservice(services_id, service_type, charge);
    }

    @DELETE
    @Path("/deleteservice/{services_id}")
    public void deleteservice(@PathParam("services_id") int services_id) {
        u.deleteservice(services_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllServices")
    public Collection<Tblservice> getAllServices() {
        return u.getAllServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getServicesByType/{service_type}")
    public Collection<Tblservice> getServicesByType(@PathParam("service_type") String service_type) {
        return u.getServicesByType(service_type);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getServicesByCharge/{charge}")
    public Collection<Tblservice> getServicesByCharge(@PathParam("charge") int charge) {
        return u.getServicesByCharge(charge);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllServiceById/{services_id}")
    public Tblservice getAllServiceById(@PathParam("services_id") int services_id) {
        return u.getAllServiceById(services_id);
    }

}
