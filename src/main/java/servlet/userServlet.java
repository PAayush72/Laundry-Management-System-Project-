package servlet;

import ejb.admin_beansLocal;
import ejb.user_beanLocal;
import entities.Tblcustomer;
import entities.Tblemployee;
import entities.Tblorder;
import entities.Tblservice;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class userServlet extends HttpServlet {

    @EJB
    user_beanLocal userBean;
    @EJB
    admin_beansLocal services;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            // Output HTML header and page title
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Customer and Order Details</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Customer Details</h1>");
            //            userBean.addCustomer("Raj", "p", "p", "800", "abc");
            LocalDate order_date = LocalDate.of(2024, 10, 20);
            LocalDate pickup_date = LocalDate.of(2024, 10, 21);
            LocalDate delivery_date = LocalDate.of(2024, 10, 22);

            Date orderDate = Date.from(order_date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date pickupDate = Date.from(pickup_date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date deliveryDate = Date.from(delivery_date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            services.addEmployee("pl", 3, 5000);
//            userBean.addorder(9, orderDate, pickupDate, deliveryDate, "not",3);
//userBean.updateOrder(8, 6, orderDate, pickupDate, deliveryDate, "Done", 3);
//              userBean.removeCustomer(4);
//               userBean.deleteOrder(7);
//            userBean.addorderItem(5, 2, 8, "Kurta", 1);
//            userBean.addorderItem(5, 1, 8, "Blazer", 1);
//            userBean.addorderItem(5, 3, 8, "Paijama", 1);
//           Collection<Tblorder>o= userBean.getOrderByDelivery_Date(deliveryDate);
//           for(Tblorder r : o){
//               out.println("delivay date:"+r.getDeliveryDate());
//           }

            Collection<Tblcustomer> customers = userBean.getAllCustomers();
            for (Tblcustomer customer : customers) {
                out.println("<h3>Customer ID: " + customer.getCustomerId()
                        + ", Name: " + customer.getCustomerName()
                        + ", Address: " + customer.getCustomerAddress()
                        + ", Email: " + customer.getEmail()
                        + ", Phone: " + customer.getPhno() + "</h3>");

                // Get orders for this customer
                Collection<Tblorder> orders = userBean.getOrderByCustomerId(customer.getCustomerId());

                for (Tblorder order : orders) {
                    out.println("<h4>Order Date: " + order.getOrderDate()
                            + ", Pickup Date: " + order.getPickupDate()
                            + ", Delivery Date: " + order.getDeliveryDate()
                            + ", Status: " + order.getStatus() + "</h4>");

                    // Get services for each order (associated by serviceId)
//                    Tblservice service = order.getServicesId(); // Get the related service using the order's serviceId

//                    out.println("<h3>Service: " + service.getServiceType() + " (" + service.getCharge() + ")</h3>");

//                    Tblemployee emp = service.getServicesId();
                }
//                Tblorder order = new Tblorder();
                
                out.println("<hr>");
            }
            

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet displaying customer, order, and service details.";
    }
}
