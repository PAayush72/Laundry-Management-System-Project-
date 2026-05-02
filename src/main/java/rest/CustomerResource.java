/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.user_beanLocal;
import entities.Tblcustomer;
import entities.Tblorder;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author LENOVO
 */
@Path("customer")
@RequestScoped
public class CustomerResource {

    @EJB
    user_beanLocal u;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Default
    public String sayHello() {
        return "<h1>Hello</h1>";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/all")
    public Collection<Tblcustomer> getAllCustomers() {
        return u.getAllCustomers();
    }

    @POST
    @Path("/addcust/{customer_name}/{customer_address}/{email}/{phno}/{password}/{role_id}")
    public void addCustomer(@PathParam("customer_name") String customer_name, 
            @PathParam("customer_address") String customer_address,
            @PathParam("email") String email, 
            @PathParam("phno") String phno, 
            @PathParam("password") String password, 
            @PathParam("role_id") int role_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.addCustomer(customer_name, customer_address, email, phno, password, role_id);
    }

    @GET
    @Path("/getcustbyname/{customer_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Tblcustomer> getAllCustomersByName(@PathParam("customer_name") String customer_name) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getAllCustomersByName(customer_name);
    }

    @PUT
    @Path("/updatecust/{customer_id}/{customer_name}/{customer_address}/{email}/{phno}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateCustomer(@PathParam("customer_id") int customer_id,
            @PathParam("customer_name") String customer_name,
            @PathParam("customer_address") String customer_address,
            @PathParam("email") String email,
            @PathParam("phno") String phno,
            @PathParam("password") String password) {

        u.updateCustomer(customer_id, customer_name, customer_address, email, phno, password);

//        return Response.ok("Customer updated successfully").build();
    }

    @DELETE
    @Path("removeCust/{customer_id}/{role_id}")
    public void removeCustomer(@PathParam("customer_id") int customer_id,
            @PathParam("role_id") int role_id) {
        u.removeCustomer(customer_id, role_id);
//        return Response.ok("Customer updated successfully").build();
    }

    @GET
    @Path("/getCustById/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tblcustomer getCustomersById(@PathParam("customer_id") int customer_id){
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return  u.getCustomersById(customer_id);
    }

    @GET
    @Path("/getCustByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tblcustomer getCustomersByEmail(@PathParam("email") String email) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getCustomersByEmail(email);
    }

    @GET
    @Path("/getAllCustByAddress/{customer_address}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Tblcustomer> getAllCustomersByAddress(@PathParam("customer_address") String customer_address) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Tblcustomer> address = u.getAllCustomersByAddress(customer_address);
        return address;
    }

    @GET
    @Path("/getCustByPhno/{phno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Tblcustomer> getCustomersByPhno(@PathParam("phno") String phno) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Tblcustomer> phone_no = u.getCustomersByPhno(phno);
        return phone_no;
    }
}
