/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.user_beanLocal;
import entities.Tblcustomer;
import entities.Tblorder;
import entities.Tblpayment;
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
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author LENOVO
 */
@Path("Payment")
@RequestScoped
public class PaymentResource {

    @EJB
    user_beanLocal u;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PaymentResource
     */
    public PaymentResource() {
    }

    @POST
    @Path("/addpayment/{customer_id}/{order_id}/{amount}/{method}")
    public void addpayment(@PathParam("customer_id") int customer_id,
            @PathParam("order_id") int order_id,
            @PathParam("amount") int amount,
            @PathParam("method") String method) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.addpayment(customer_id, order_id, amount, method);
    }

    @PUT
    @Path("/updatepayment/{pay_id}/{customer_id}/{order_id}/{amount}/{method}")
    public void updatepayment(@PathParam("pay_id") int pay_id,
            @PathParam("customer_id") int customer_id,
            @PathParam("order_id") int order_id,
            @PathParam("amount") int amount,
            @PathParam("method") String method) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       u.updatepayment(pay_id, customer_id, order_id, amount, method);
    }
}
