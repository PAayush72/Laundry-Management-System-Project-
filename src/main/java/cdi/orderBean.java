package cdi;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import ejb.user_beanLocal;
import entities.Tblorder;
import entities.Tblcustomer;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mail.mail;

@Named(value = "orderBean")
@ViewScoped
public class orderBean implements Serializable {

    @Inject
    private mail emailSender;
    @EJB
    private user_beanLocal user_bean;
    Tblorder updateOrder;
    private static final Logger LOGGER = Logger.getLogger(orderBean.class.getName());
    Response rs;
    private Tblorder order;
    private Collection<Tblorder> ord;
    private GenericType<Collection<Tblorder>> gord;
    private int orderId;
    private int customerId;
    private String userEmail;
    private Date delivery_date;
    private Date pickup_date;
    private Date order_date;

    private String status = "Not";

    @Inject
    private LoginMB loginMB;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//
//        LoginMB loginMB = (LoginMB) session.getAttribute("loginMB");
//        if (loginMB != null) {
//            
//            LOGGER.log(Level.INFO, "Customer ID set: {0}", this.customerId);
//        }
//        FacesContext context = FacesContext.getCurrentInstance();

        this.customerId = loginMB.getC().getCustomerId();
        this.userEmail = loginMB.getC().getEmail();

        String updateString = facesContext.getExternalContext().getRequestParameterMap().get("orderId");

        if (updateString != null) {
            try {
                this.orderId = Integer.parseInt(updateString);
                loadOrderDetails(orderId);
                System.out.println("Username:" + this.order.getOrderId());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadOrderDetails(int id) {
//        System.out.println("Posts count:"+this.posts.size());
        this.order = user_bean.getOrderById(id);
    }

    public class OrderDTO {

        private Integer orderId;
        private String status;

        // Getters and Setters
        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Tblorder getUpdateOrder() {
        return updateOrder;
    }

    public void setUpdateOrder(Tblorder updateOrder) {
        this.updateOrder = updateOrder;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Response getRs() {
        return rs;
    }

    public void setRs(Response rs) {
        this.rs = rs;
    }

    public GenericType<Collection<Tblorder>> getGord() {
        return gord;
    }

    public void setGord(GenericType<Collection<Tblorder>> gord) {
        this.gord = gord;
    }

    // Getter and Setter methods
    public Tblorder getOrder() {
        return order;
    }

    public void setOrder(Tblorder order) {
        this.order = order;
    }

    public Collection<Tblorder> getOrd() {
        return ord;
    }

    public void setOrd(Collection<Tblorder> ord) {
        this.ord = ord;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Date getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(Date pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String addorder() {
        try {
            // Convert pickup_date and delivery_date strings to Date (adjust format if necessary)
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date pickupDate = null;
//            Date deliveryDate = null;
            Date orderDate = new Date();  // Set the current date if no specific date is provided

//            if (pickup_date != null && !pickup_date.isEmpty()) {
//                pickupDate = dateFormat.parse(pickup_date);
//            }
//
//            if (delivery_date != null && !delivery_date.isEmpty()) {
//                deliveryDate = dateFormat.parse(delivery_date);
//            }

            // Add the order using user_bean (service layer)
            user_bean.addorder(customerId, orderDate, pickup_date, delivery_date, status);

            // Fetch all orders for the customer (a collection of orders)
            Collection<Tblorder> orders = user_bean.getOrderByCustomerId(customerId);

            // If you need to find the latest order (e.g., based on orderDate or some other criteria)
            Tblorder latestOrder = null;
            for (Tblorder order1 : orders) {
                if (latestOrder == null || order1.getOrderDate().after(latestOrder.getOrderDate())) {
                    latestOrder = order1;  // Find the latest order
                }
            }

            // Log the generated order ID
            if (latestOrder != null) {
                System.out.println("Order ID generated: " + latestOrder.getOrderId());

                // Store the latest order in the session
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("orderBean", latestOrder);  // Store the latest order in session

                // Log session storage
                System.out.println("Stored Order ID in session: " + latestOrder.getOrderId());
            } else {
                System.out.println("No orders found for this customer.");
            }
//            emailSender.sendEmail(userEmail);
            // Return a redirect to the order item page
            return "orderItem?faces-redirect=true";  // Redirect to the next page
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in addorder method: {0}", e.getMessage());
            return null;
        }
    }

    public void deleteOrder(int orderId,int customerId) {
        System.out.println("before>>>>>>>>>" + orderId);
//        this.orderId = order.getOrderId();
        user_bean.deleteOrder(orderId,customerId);
//        System.out.println("after>>>>>>>>>" + orderId);
    }

    public Collection<Tblorder> getAllOrders() {
        return user_bean.getAllOrders();
    }

    public Collection<Tblorder> getOrderByCustomerId(int customer_id) {
        return user_bean.getOrderByCustomerId(customer_id);
    }

    public void updateOrder() throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date pickupDate = null;
//        Date deliveryDate = null;
        Date orderDate = new Date();
//        pickupDate = dateFormat.parse(pickup_date);
//        deliveryDate = dateFormat.parse(delivery_date);

        user_bean.updateOrder(this.order.getOrderId(), customerId, orderDate, pickup_date, delivery_date, status);
    }

    public orderBean() {
        order = new Tblorder();
        ord = new ArrayList<>();
        gord = new GenericType<Collection<Tblorder>>() {
        };
    }
}
