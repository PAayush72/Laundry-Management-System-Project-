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
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author LENOVO
 */
@Stateless
public class user_bean implements user_beanLocal {

    @PersistenceContext(unitName = "project_unit")
    EntityManager em;

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

    @Override
    public void addorder(int customer_id, Date order_date, Date pickup_date, Date delivery_date, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
//        Tblservice se = (Tblservice) em.find(Tblservice.class, services_id);
//        Tblorder o = (Tblorder) em.find(Tblorder.class, order_id);

        Collection<Tblorder> Tblorder = cust.getTblorderCollection();
        Tblorder a = new Tblorder();
        a.setCustomerId(cust);
        a.setOrderDate(order_date);
        a.setPickupDate(pickup_date);
        a.setDeliveryDate(delivery_date);
        a.setStatus(status);
//        a.setServicesId(se);

        Tblorder.add(a);
        cust.setTblorderCollection(Tblorder);
//        se.setTblorderCollection(Tblorder);

        em.persist(a);
        em.merge(cust);
//        em.merge(se);
    }

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
    public Collection<Tblcustomer> getCustomersByEmail(String email) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblcustomer.findByEmail")
                .setParameter("email", email)
                .getResultList();
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
    public Collection<Tblcustomer> getCustomersById(int customer_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblcustomer.findByCustomerId")
                .setParameter("customerId", customer_id)
                .getResultList();
    }

    @Override
    public void updateOrder(int order_id, int customer_id, Date order_date, Date pickup_date, Date delivery_date, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
//        Tblservice s = (Tblservice) em.find(Tblservice.class, services_id);

        ord.setCustomerId(cust);
        ord.setOrderDate(order_date);
        ord.setPickupDate(pickup_date);
        ord.setDeliveryDate(delivery_date);
        ord.setStatus(status);
//        ord.setServicesId(s);

        em.merge(ord);
    }

    @Override
    public void deleteOrder(int order_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);

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
    public Collection<Tblorder> getOrderById(int order_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblorder.findByOrderId")
                .setParameter("orderId", order_id)
                .getResultList();
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
    @Override
    public void addorderItem(int customer_id, int service_id, int order_id, String material, int qty, String photo) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblservice service = (Tblservice) em.find(Tblservice.class, service_id);
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        Collection<Tblorderitem> item = service.getTblorderitemCollection();
        Collection<Tblorderitem> c = cust.getTblorderitemCollection();
        Collection<Tblorderitem> o = ord.getTblorderitemCollection();

        Tblorderitem i = new Tblorderitem();
        i.setCustomerId(cust);
        i.setOrderId(ord);
        i.setServiceId(service);
        i.setMaterial(material);
        i.setQty(qty);
        i.setPhoto(photo);

        item.add(i);
        c.add(i);
        o.add(i);
        cust.setTblorderitemCollection(item);
        ord.setTblorderitemCollection(item);
        service.setTblorderitemCollection(item);

        em.persist(i);
        em.merge(cust);
        em.merge(service);
        em.merge(ord);

    }

    @Override
    public void updateOrderItem(int order_item_id, int customer_id, int services_id, int order_id, String material, int qty, String photo) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorder ord = (Tblorder) em.find(Tblorder.class, order_id);
        Tblcustomer cust = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        Tblservice s = (Tblservice) em.find(Tblservice.class, services_id);
        Tblorderitem item = (Tblorderitem) em.find(Tblorderitem.class, order_item_id);

        item.setOrderItemId(order_item_id);
        item.setCustomerId(cust);
        item.setServiceId(s);
        item.setOrderId(ord);
        item.setMaterial(material);
        item.setQty(qty);
        item.setPhoto(photo);

        em.merge(item);

    }

    @Override
    public void deleteOrderItem(int order_item_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorderitem i = (Tblorderitem) em.find(Tblorderitem.class, order_item_id);

        em.remove(i);
    }

    @Override
    public Collection<Tblorderitem> getAllOrderitem() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblorderitem.findAll").getResultList();

    }

    @Override
    public Collection<Tblorderitem> getAllOrderitemBycustomerId(int customer_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblcustomer c = (Tblcustomer) em.find(Tblcustomer.class, customer_id);
        return c.getTblorderitemCollection();
    }

    @Override
    public Collection<Tblorderitem> getAllOrderitemByServiceId(int service_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblservice s = (Tblservice) em.find(Tblservice.class, service_id);
        return s.getTblorderitemCollection();
    }

    @Override
    public Collection<Tblorderitem> getAllOrderitemByOrderId(int order_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblorder o = (Tblorder) em.find(Tblorder.class, order_id);
        return o.getTblorderitemCollection();
    }

    @Override
    public Collection<Tblorderitem> getAllOrderitemByMaterial(String material) {
        // Use the correct named query to filter by material
        return em.createNamedQuery("Tblorderitem.findByMaterial")
                .setParameter("material", material)
                .getResultList();
    }

    @Override
    public Collection<Tblorderitem> getAllOrderitemById(int order_item_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblorderitem.findByOrderItemId")
                .setParameter("orderItemId", order_item_id)
                .getResultList();
    }

    @Override
    public void addpayment(int customer_id, int order_id, int amount, String method) {
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
    public void updatepayment(int pay_id, int customer_id, int order_id, int amount, String method) {
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
    public void deletePayment(int pay_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Tblpayment> getAllPaymentDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Tblpayment> getPaymentByCustId(int customer_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Tblpayment> getPaymentByOrderId(int order_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Tblpayment> getPaymentByMethod(String method) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
