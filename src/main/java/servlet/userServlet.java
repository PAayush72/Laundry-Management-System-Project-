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
//            LocalDate order_date = LocalDate.now();
//          
//              userBean.removeCustomer(4);  LocalDate pickup_date = LocalDate.of(2024, 10, 21);
//            LocalDate delivery_date = LocalDate.of(2024, 10, 22);
//
////            Date orderDate = Date.from(order_date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            Date pickupDate = Date.from(pickup_date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            Date deliveryDate = Date.from(delivery_date.atStartOfDay(ZoneId.systemDefault()).toInstant());
////            services.addEmployee("pl", 3, 5000);
//            userBean.addorder(42, deliveryDate, pickupDate, deliveryDate, "not");
////userBean.updateOrder(8, 6, orderDate, pickupDate, deliveryDate, "Done", 3);
//               userBean.deleteOrder(7);
//            userBean.addorderItem(5, 2, 8, "Kurta", 1);
//            userBean.addorderItem(5, 1, 8, "Blazer", 1);
//            userBean.addorderItem(5, 3, 8, "Paijama", 1);
//           Collection<Tblorder>o= userBean.getOrderByDelivery_Date(deliveryDate);
//           for(Tblorder r : o){
//               out.println("delivay date:"+r.getDeliveryDate());
//           }
userBean.deleteOrder(175,45);

            
//                Tblorder order = new Tblorder();

                out.println("<hr>");
            

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
