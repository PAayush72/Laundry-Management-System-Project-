/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entities.Tblcustomer;
import entities.Tblorder;
import entities.Tblorderitem;
import entities.Tblpayment;
import entities.Tblservice;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author LENOVO
 */
@Local
public interface user_beanLocal {
    //Customer

    Collection<Tblcustomer> getAllCustomer();

    void addCustomer(String customer_name, String customer_address, String email, String phno, String password,int role_id);

    void updateCustomer(int customer_id, String customer_name, String customer_address, String email, String phno, String password);

    void removeCustomer(int customer_id,int role_id);

    Collection<Tblcustomer> getAllCustomers();

    Collection<Tblcustomer> getCustomersById(int customer_id);

    Collection<Tblcustomer> getAllCustomersByName(String customer_name);

    Collection<Tblcustomer> getCustomersByEmail(String email);

    Collection<Tblcustomer> getAllCustomersByAddress(String customer_address);

    Collection<Tblcustomer> getCustomersByPhno(String phno);

    //orders
    void addorder(int customer_id, Date order_date, Date pickup_date, Date delivery_date, String status);

    void updateOrder(int order_id, int customer_id, Date order_date, Date pickup_date, Date delivery_date, String status);

    void deleteOrder(int order_id);

    Collection<Tblorder> getAllOrders();

    Collection<Tblorder> getOrderByCustomerId(int customer_id);

//    Collection<Tblorder> getOrderByServiceId(int services_id);
//    Tblorder getserviceId(int services_id);
    Collection<Tblorder> getOrderById(int order_id);

    Collection<Tblorder> getOrderByOrderDate(Date order_date);

    Collection<Tblorder> getOrderByPickup_Date(Date pickup_date);

    Collection<Tblorder> getOrderByDelivery_Date(Date delivery_date);

    Collection<Tblorder> getOrderByStatus(String status);

//    Collection<Tblorder> getOrderBymaterial(String material);
    //service
//     Collection<Tblservice> getCustomerById(int customer_id);
    //Orderitem
    void addorderItem(int customer_id, int services_id, int order_id, String material, int qty, String photo);

    void updateOrderItem(int order_item_id, int customer_id, int service_id, int order_id, String material, int qty, String photo);

    void deleteOrderItem(int order_item_id);

    Collection<Tblorderitem> getAllOrderitem();

    Collection<Tblorderitem> getAllOrderitemBycustomerId(int customer_id);

    Collection<Tblorderitem> getAllOrderitemByServiceId(int service_id);

    Collection<Tblorderitem> getAllOrderitemByOrderId(int order_id);

    Collection<Tblorderitem> getAllOrderitemById(int order_item_id);

    Collection<Tblorderitem> getAllOrderitemByMaterial(String material);

    //Payment
    void addpayment(int customer_id, int order_id, int amount, String method);

    void updatepayment(int pay_id, int customer_id, int order_id, int amount, String method);

    void deletePayment(int pay_id);

    Collection<Tblpayment> getAllPaymentDetails();

    Collection<Tblpayment> getPaymentByCustId(int customer_id);

    Collection<Tblpayment> getPaymentByOrderId(int order_id);

    Collection<Tblpayment> getPaymentByMethod(String method);

}
