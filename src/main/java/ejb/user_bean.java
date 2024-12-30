/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entities.Tblcustomer;
import entities.Tblorder;
import entities.Tblorderitem;
import entities.Tblpayment;
import entities.Tblrole;
import entities.Tblservice;
import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author LENOVO
 */
@Stateless
public class user_bean implements user_beanLocal {

    @PersistenceContext(unitName = "project_unit")
    EntityManager em;

//    public class OrderDTO {
//
//        private Integer orderId;
//        private String status;
//
//        // Getters and Setters
//        public Integer getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(Integer orderId) {
//            this.orderId = orderId;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//    }
    @Override
    public Collection<Tblcustomer> getAllCustomer() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Tblcustomer> cust = em.createNamedQuery("Tblcustomer.findAll").getResultList();
        return cust;
    }

    @Override
    public void addCustomer(String customer_name, String customer_address, String email, String phno, String password, int role_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblrole role = (Tblrole) em.find(Tblrole.class, role_id);
        Collection<Tblcustomer> cust = role.getTblcustomerCollection();
        Tblcustomer tbl = new Tblcustomer();
        tbl.setCustomerName(customer_name);
        tbl.setCustomerAddress(customer_address);
        tbl.setEmail(email);
        tbl.setPhno(phno);
        tbl.setPassword(password);
        tbl.setRoleId(role);

        cust.add(tbl);
        role.setTblcustomerCollection(cust);
        em.persist(tbl);
        em.merge(role);
    }

//    @Transactional
    @Override
    public void addorder(int customer_id, Date order_date, Date pickup_date, Date delivery_date, String status) {
        // Find the customer by their ID
        Tblcustomer cust = em.find(Tblcustomer.class, customer_id);
        if (cust == null) {
            throw new IllegalArgumentException("Customer not found with ID: " + customer_id);
        }

        // Use the provided order_date
        Date orderDate = order_date != null ? order_date : new Date();

        // Create a new order
        Tblorder newOrder = new Tblorder();
        newOrder.setCustomerId(cust);
        newOrder.setOrderDate(orderDate);
        newOrder.setPickupDate(pickup_date);
        newOrder.setDeliveryDate(delivery_date);
        newOrder.setStatus(status);

        // Persist the new order
        em.persist(newOrder);
        em.flush();  // Ensure the order ID is generated

//        Integer ordId = newOrder.getOrderId();
//        addorderItem(service_id,newOrder.ordId,material,qty,photo);
        // Update the customer's order collection
        Collection<Tblorder> orders = cust.getTblorderCollection();
        orders.add(newOrder);
        cust.setTblorderCollection(orders);

        // Merge the customer entity to update the order collection
        em.merge(cust);

        // Log the order ID
        Logger.getLogger(this.getClass().getName()).info("Order added with ID: " + newOrder.getOrderId());
    }

//    @Override
//    public Tblorder getLatestOrderForCustomer(int customerId) {
//        TypedQuery<Tblorder> query = em.createQuery(
//                "SELECT o FROM Tblorder o WHERE o.customerId.customerId = :customerId ORDER BY o.orderId DESC", Tblorder.class);
//        query.setParameter("customerId", customerId);
//        query.setMaxResults(1);
//        return query.getResultList().stream().findFirst().orElse(null);
//    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void removeCustomer(int customer_id, int role_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        Tblrole role = (Tblrole) em.find(Tblrole.class, role_id);
        Collection<Tblcustomer> c = role.getTblcustomerCollection();

        c.remove(cust);
        em.merge(cust);
        em.remove(cust);

    }

    @Override
    public Collection<Tblcustomer> getAllCustomers() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblcustomer.findAll").getResultList();
    }

    @Override
    public Collection<Tblcustomer> getAllCustomersByName(String customer_name) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblcustomer.findByCustomerName")
                .setParameter("customerName", customer_name)
                .getResultList();

    }

    @Override
    public Tblcustomer getCustomersByEmail(String email) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return (Tblcustomer) em.createNamedQuery("Tblcustomer.findByEmail")
                .setParameter("email", email).getResultList().iterator().next();
    }

    @Override
    public Collection<Tblcustomer> getAllCustomersByAddress(String customer_address) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblcustomer.findByCustomerAddress")
                .setParameter("customerAddress", customer_address)
                .getResultList();
    }

    @Override
    public Collection<Tblcustomer> getCustomersByPhno(String phno) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblcustomer.findByPhno")
                .setParameter("phno", phno)
                .getResultList();
    }

    @Override
    public void updateCustomer(int customer_id, String customer_name, String customer_address, String email, String phno, String password) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        cust.setCustomerName(customer_name);
        cust.setCustomerAddress(customer_address);
        cust.setEmail(email);
        cust.setPhno(phno);
        cust.setPassword(password);

        em.merge(cust);
    }

    @Override
    public Tblcustomer getCustomersById(int customer_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return (Tblcustomer) em.createNamedQuery("Tblcustomer.findByCustomerId")
                .setParameter("customerId", customer_id)
                .getResultList().iterator().next();
    }

    @Override
    public void updateOrder(int order_id, int customer_id, Date order_date, Date pickup_date, Date delivery_date, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
//        Tblservice s = (Tblservice) em.find(Tblservice.class, services_id);
        Date orderDate = order_date != null ? order_date : new Date();

        ord.setCustomerId(cust);
        ord.setOrderDate(orderDate);
        ord.setPickupDate(pickup_date);
        ord.setDeliveryDate(delivery_date);
        ord.setStatus(status);
//        ord.setServicesId(s);

        em.merge(ord);
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        try {
            // Find the order by orderId
            Tblorder order = em.find(Tblorder.class, orderId);

            if (order != null) {
                // Set the new status
                order.setStatus(status);

                // Persist the changes (update the order in the database)
                em.merge(order);
            } else {
                // Handle the case when the order is not found (optional)
                System.out.println("Order not found for ID: " + orderId);
            }
        } catch (Exception e) {
            // Handle any exceptions (e.g., log the error)
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int order_id, int customer_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        Collection<Tblorder> o = cust.getTblorderCollection();

        o.remove(ord);
        em.merge(ord);
        em.remove(ord);
    }

    @Override
    public Collection<Tblorder> getAllOrders() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblorder.findAll").getResultList();

    }

    @Override
    public Collection<Tblorder> getOrderByCustomerId(int customer_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    return em.createNamedQuery("Tblorder.findByCustomerId")
//                .setParameter("customer_id", customer_id)
//                .getResultList();
        Tblcustomer c = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        return c.getTblorderCollection();
    }

    @Override
    public Tblorder getOrderById(int order_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return (Tblorder) em.createNamedQuery("Tblorder.findByOrderId")
                .setParameter("orderId", order_id)
                .getResultList().iterator().next();
    }

    @Override
    public Collection<Tblorder> getOrderByOrderDate(Date order_date) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        return em.createNamedQuery("Tblorder.findByOrderDate")
//                .setParameter("order_date", order_date)
//                .getResultList();
        return em.createNamedQuery("Tblorder.findByOrderDate")
                .setParameter("orderDate", order_date)
                .getResultList();
    }

    @Override
    public Collection<Tblorder> getOrderByPickup_Date(Date pickup_date) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblorder.findByPickupDate")
                .setParameter("pickupDate", pickup_date)
                .getResultList();
    }

    @Override
    public Collection<Tblorder> getOrderByDelivery_Date(Date delivery_date) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblorder.findByDeliveryDate")
                .setParameter("deliveryDate", delivery_date)
                .getResultList();
    }

    @Override
    public Collection<Tblorder> getOrderByStatus(String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblorder.findByStatus")
                .setParameter("status", status)
                .getResultList();
    }

//    @Override
//    public Collection<Tblorder> getOrderByServiceId(int service_id) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        Tblservice c = (Tblservice) em.find(Tblservice.class, service_id);
//        return c.getTblorderCollection();
////        Tblcustomer c = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
////        return c.getTblorderCollection();
//    }
//    @Override
//    public Tblorder getserviceId(int services_id) {
//        Tblservice a = (Tblservice) em.find(Tblservice.class, services_id);
//
//        // Assuming Tblservice has a field like 'order' that references Tblorder:
//        return a.getServicesId();  // Return the Tblorder object associated with the Tblservice
//    }
//    @Transactional
    @Override
    public Response addorderItem(int service_id, int order_id, String material, int qty, String photo) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblservice service = (Tblservice) em.find(Tblservice.class, service_id);
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);

        Collection<Tblorderitem> item = service.getTblorderitemCollection();

        Collection<Tblorderitem> o = ord.getTblorderitemCollection();

        Tblorderitem i = new Tblorderitem();

        i.setOrderId(ord);
        i.setServiceId(service);
        i.setMaterial(material);
        i.setQty(qty);
        i.setPhoto(photo);

        item.add(i);

        o.add(i);

        ord.setTblorderitemCollection(item);
        service.setTblorderitemCollection(item);

        em.persist(i);
        em.flush();
        em.merge(service);
        em.merge(ord);
        try {
            URI createdUri = UriBuilder.fromPath("/orders/{orderId}/items/{itemId}")
                    .build(ord.getOrderId(), i.getOrderItemId());

            // Return a 201 Created response with the URI and a success message
            return Response.created(createdUri)
                    .entity("Order item successfully added.")
                    .build();

        } catch (Exception e) {
            // Log the error and handle exceptions
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while adding the order item")
                    .build();

        }
    }

    @Override
    public void updateOrderItem(int order_item_id, int services_id, int order_id, String material,
            int qty, String photo) {
        if (order_id == 0) {
            throw new IllegalArgumentException("Invalid order_id: 0 is not a valid order ID.");
        }

        // Fetch the Tblorder, Tblservice, and Tblorderitem entities from the database
        Tblorder ord = em.find(Tblorder.class, order_id);
        Tblservice s = em.find(Tblservice.class, services_id);
        Tblorderitem item = em.find(Tblorderitem.class, order_item_id);

        // Check if any of the retrieved entities are null and handle it
        if (ord == null) {
            throw new IllegalArgumentException("Order with ID " + order_id + " not found.");
        }
        if (s == null) {
            throw new IllegalArgumentException("Service with ID " + services_id + " not found.");
        }
        if (item == null) {
            throw new IllegalArgumentException("OrderItem with ID " + order_item_id + " not found.");
        }

        // Set the updated values to the order item
        item.setServiceId(s);
        item.setOrderId(ord);
        item.setMaterial(material);
        item.setQty(qty);
        item.setPhoto(photo);

        // Merge the updated item into the database
        em.merge(item);
    }

    @Override
    public void deleteOrderItem(int order_item_id, int order_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorderitem i = (Tblorderitem) em.find(Tblorderitem.class, order_item_id);
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);
        Collection<Tblorderitem> o = ord.getTblorderitemCollection();
        o.remove(i);
        em.merge(i);
        em.remove(i);
    }

    @Override
    public Collection<Tblorderitem> getAllOrderitem() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblorderitem.findAll").getResultList();

    }

    @Override
    public Collection<Tblorderitem> getAllOrderitemByServiceId(int service_id
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblservice s = (Tblservice) em.find(Tblservice.class, service_id);
        return s.getTblorderitemCollection();
    }

    @Override
    public List<Tblorderitem> getAllOrderitemByOrderId(int orderId) {
        // First, retrieve the Tblorder using the orderId
        Tblorder order = em.find(Tblorder.class, orderId);

        if (order == null) {
            return new ArrayList<>(); // Return an empty list if the order doesn't exist
        }

        // Now, query the Tblorderitem based on the Tblorder object
        TypedQuery<Tblorderitem> query = em.createQuery("SELECT oi FROM Tblorderitem oi WHERE oi.orderId = :order", Tblorderitem.class);
        query.setParameter("order", order);  // Set the Tblorder object as the parameter
        return query.getResultList();  // Return the list of order items
    }

    @Override
    public Collection<Tblorderitem> getAllOrderitemByMaterial(String material
    ) {
        // Use the correct named query to filter by material
        return em.createNamedQuery("Tblorderitem.findByMaterial")
                .setParameter("material", material)
                .getResultList();
    }

    @Override
    public Tblorderitem getAllOrderitemById(int order_item_id
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return (Tblorderitem) em.createNamedQuery("Tblorderitem.findByOrderItemId")
                .setParameter("orderItemId", order_item_id)
                .getResultList().iterator().next();
    }

    @Override
    public void addpayment(int customer_id, int order_id, double amount, String method
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);

        Collection<Tblpayment> c = cust.getTblpaymentCollection();
        Collection<Tblpayment> o = ord.getTblpaymentCollection();
        Tblpayment pay = new Tblpayment();

        pay.setCustomerId(cust);
        pay.setOrderId(ord);
        pay.setAmount(amount);
        pay.setMethod(method);

        c.add(pay);
        o.add(pay);

        cust.setTblpaymentCollection(c);
        ord.setTblpaymentCollection(o);

        em.persist(pay);
        em.merge(cust);
        em.merge(ord);
    }

    @Override
    public void updatepayment(int pay_id, int customer_id, int order_id, double amount, String method
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        Tblpayment pay = (Tblpayment) em.find(Tblpayment.class, pay_id);
        pay.setCustomerId(cust);
        pay.setOrderId(ord);
        pay.setAmount(amount);
        pay.setMethod(method);

        em.merge(pay);
    }

    @Override
    public void deletePayment(int pay_id
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Tblpayment> getAllPaymentDetails() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblpayment.findAll").getResultList();
    }

    @Override
    public Collection<Tblpayment> getPaymentByCustId(int customer_id
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Tblpayment> getPaymentByOrderId(int order_id
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Tblpayment> getPaymentByMethod(String method
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
