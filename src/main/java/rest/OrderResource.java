/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.user_beanLocal;
import entities.Tblcustomer;
import entities.Tblorder;
import entities.Tblorderitem;
import entities.Tblservice;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author LENOVO
 */
@Path("order")
@RequestScoped
public class OrderResource {

    @EJB
    user_beanLocal u;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OrderResource
     */
    public OrderResource() {
    }

    /**
     * Retrieves representation of an instance of rest.OrderResource // * @param
     * order_date // * @param orderDate
     *
     * @param order_date
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getorderdate/{order_date}")
    public Collection<Tblorder> getOrderByOrderDate(@PathParam("order_date") String order_date) {
        try {
            // Parse the order date from string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(order_date);

            // Call the EJB method to fetch orders by the parsed date
            return u.getOrderByOrderDate(parsedDate);

        } catch (ParseException e) {
// Handle exceptions (e.g., invalid date format)
            throw new WebApplicationException("Invalid date format. Please use yyyy-MM-dd.", 400);
        }

    }

    @POST
    @Path("/addorder/{customer_id}/{order_date}/{pickup_date}/{delivery_date}/{status}")
    public void addorder(@PathParam("customer_id") int customer_id,
            @PathParam("order_date") String order_date,
            @PathParam("pickup_date") String pickup_date,
            @PathParam("delivery_date") String delivery_date,
            @PathParam("status") String status
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(order_date);
            Date pickupdate = dateFormat.parse(pickup_date);
            Date deliverydate = dateFormat.parse(delivery_date);

            u.addorder(customer_id, parsedDate, pickupdate, deliverydate, status);
        } catch (ParseException e) {
// Handle exceptions (e.g., invalid date format)
            throw new WebApplicationException("Invalid date format. Please use yyyy-MM-dd.", 400);
        }
    }

    @PUT
    @Path("/UpdateOrder/{order_id}/{customer_id}/{order_date}/{pickup_date}/{delivery_date}/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(
            @PathParam("order_id") int order_id,
            @PathParam("customer_id") int customer_id,
            @PathParam("order_date") String order_date,
            @PathParam("pickup_date") String pickup_date,
            @PathParam("delivery_date") String delivery_date,
            @PathParam("status") String status) {

        try {
            // Date parsing with a format of "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedOrderDate = dateFormat.parse(order_date);
            Date parsedPickupDate = dateFormat.parse(pickup_date);
            Date parsedDeliveryDate = dateFormat.parse(delivery_date);

            // Update order details
            u.updateOrder(order_id, customer_id, parsedOrderDate, parsedPickupDate, parsedDeliveryDate, status);

            // Return HTTP 200 OK with a success message
            return Response.ok("Order updated successfully").build();
        } catch (ParseException e) {
            // If there's an error with date parsing, return HTTP 400 Bad Request with a message
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Please use yyyy-MM-dd.")
                    .build();
        } catch (Exception e) {
            // Handle other exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/DeleteOrder/{order_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteOrder(@PathParam("order_id") int order_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.deleteOrder(order_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOrders")
    public Collection<Tblorder> getAllOrders() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getAllOrders();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOrderByCustomerId/{customer_id}")
    public Collection<Tblorder> getOrderByCustomerId(@PathParam("customer_id") int customer_id) {
        return u.getOrderByCustomerId(customer_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOrderById/{order_id}")
    public Collection<Tblorder> getOrderById(@PathParam("order_id") int order_id) {
        return u.getOrderById(order_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOrderByPickup_Date/{pickup_date}")
    public Collection<Tblorder> getOrderByPickup_Date(@PathParam("pickup_date") String pickup_date) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            // Parse the order date from string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(pickup_date);

            // Call the EJB method to fetch orders by the parsed date
            return u.getOrderByPickup_Date(parsedDate);

        } catch (ParseException e) {
// Handle exceptions (e.g., invalid date format)
            throw new WebApplicationException("Invalid date format. Please use yyyy-MM-dd.", 400);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOrderByDelivery_Date/{delivery_date}")
    public Collection<Tblorder> getOrderByDelivery_Date(@PathParam("delivery_date") String delivery_date) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            // Parse the order date from string to Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(delivery_date);

            // Call the EJB method to fetch orders by the parsed date
            return u.getOrderByDelivery_Date(parsedDate);

        } catch (ParseException e) {
// Handle exceptions (e.g., invalid date format)
            throw new WebApplicationException("Invalid date format. Please use yyyy-MM-dd.", 400);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOrderByStatus/{status}")
    public Collection<Tblorder> getOrderByStatus(@PathParam("status") String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getOrderByStatus(status);
    }

    @POST
    @Path("/addorderitem/{customer_id}/{service_id}/{order_id}/{material}/{qty}/{photo}")
    public void addorderItem(@PathParam("customer_id") int customer_id,
            @PathParam("service_id") int service_id,
            @PathParam("order_id") int order_id,
            @PathParam("material") String material,
            @PathParam("qty") int qty,
            @PathParam("photo") String photo) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.addorderItem(customer_id, service_id, order_id, material, qty, photo);
    }

    @PUT
    @Path("/updateOrderItem/{order_item_id}/{customer_id}/{services_id}/{order_id}/{material}/{qty}/{photo}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateOrderItem(@PathParam("order_item_id") int order_item_id,
            @PathParam("customer_id") int customer_id,
            @PathParam("services_id") int services_id,
            @PathParam("order_id") int order_id,
            @PathParam("material") String material,
            @PathParam("qty") int qty,
            @PathParam("photo") String photo) {
        u.updateOrderItem(order_item_id, customer_id, services_id, order_id, material, qty, photo);
    }

    @DELETE
    @Path("/deleteOrderItem/{order_item_id}")
    public void deleteOrderItem(@PathParam("order_item_id") int order_item_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.deleteOrderItem(order_item_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOrderitem")
    public Collection<Tblorderitem> getAllOrderitem() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getAllOrderitem();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOrderitemBycustomerId/{customer_id}")
    public Collection<Tblorderitem> getAllOrderitemBycustomerId(@PathParam("customer_id") int customer_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getAllOrderitemBycustomerId(customer_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOrderitemByServiceId/{service_id}")
    public Collection<Tblorderitem> getAllOrderitemByServiceId(@PathParam("service_id") int service_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getAllOrderitemByServiceId(service_id);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOrderitemByOrderId/{order_id}")
    public Collection<Tblorderitem> getAllOrderitemByOrderId(@PathParam("order_id") int order_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getAllOrderitemByOrderId(order_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOrderitemByMaterial/{material}")
    public Collection<Tblorderitem> getAllOrderitemByMaterial(@PathParam("material") String material) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getAllOrderitemByMaterial(material);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOrderitemById/{order_item_id}")
    public Collection<Tblorderitem> getAllOrderitemById(@PathParam("order_item_id") int order_item_id) {
        // Call the method to fetch order items by order_item_id
        return u.getAllOrderitemById(order_item_id);
    }

}
