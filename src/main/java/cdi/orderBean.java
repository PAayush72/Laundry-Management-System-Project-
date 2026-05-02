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
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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

    private String status = "not";

    @Inject
    private LoginMB loginMB;

    // Add these new properties
    private String filterStatus;
    private Integer filterCustomerId;
    private Collection<Tblorder> filteredOrders;

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
        this.today = normalizeDate(new Date());
    }

    private void loadOrderDetails(int id) {
//        System.out.println("Posts count:"+this.posts.size());
        this.order = user_bean.getOrderById(id);
    }

    private Date normalizeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
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

    private Date today;

    public Date getToday() {
        return today;
    }

    public String addorder() {
    try {
        Date orderDate = new Date(); // Today

        pickup_date = normalizeDate(pickup_date);
        delivery_date = normalizeDate(delivery_date);

        if (pickup_date.before(today)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pickup date cannot be in the past", null));
            return null;
        }

        if (delivery_date.before(pickup_date)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delivery date must be after pickup date", null));
            return null;
        }

        user_bean.addorder(customerId, orderDate, pickup_date, delivery_date, status);

        Collection<Tblorder> orders = user_bean.getOrderByCustomerId(customerId);
        Tblorder latestOrder = null;
        for (Tblorder o : orders) {
            if (latestOrder == null || o.getOrderDate().after(latestOrder.getOrderDate())) {
                latestOrder = o;
            }
        }

        if (latestOrder != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("orderBean", latestOrder);
        }

        return "orderItem?faces-redirect=true";

    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error placing order: " + e.getMessage(), null));
        return null;
    }
}


    public void deleteOrder(int orderId, int customerId) {
        System.out.println("before>>>>>>>>>" + orderId);
//        this.orderId = order.getOrderId();
        user_bean.deleteOrder(orderId, customerId);
//        System.out.println("after>>>>>>>>>" + orderId);
    }

    public Collection<Tblorder> getAllOrders() {
        return filteredOrders != null ? filteredOrders : user_bean.getAllOrders();
    }

    public Collection<Tblorder> getOrderByCustomerId(int customer_id) {
        return user_bean.getOrderByCustomerId(customer_id);
    }

    
    
    
    public void updateOrder() throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date pickupDate = null;
//        Date deliveryDate = null;
        Date orderDate = new Date();
        pickup_date = normalizeDate(pickup_date);
        delivery_date = normalizeDate(delivery_date);
//        pickupDate = dateFormat.parse(pickup_date);
//        deliveryDate = dateFormat.parse(delivery_date);

        user_bean.updateOrder(this.order.getOrderId(), customerId, orderDate, pickup_date, delivery_date, status);
    }

    public orderBean() {
        order = new Tblorder();
        ord = new ArrayList<>();
        gord = new GenericType<Collection<Tblorder>>() {
        };
        today = new Date();
        filteredOrders = null;
    }

    public Date getMaxDeliveryDate() {
        if (pickup_date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(pickup_date);
        cal.add(Calendar.DAY_OF_MONTH, 10); // Add 10 days to pickup date
        return cal.getTime();
    }

    // Add getters and setters
    public String getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(String filterStatus) {
        this.filterStatus = filterStatus;
    }

    public Integer getFilterCustomerId() {
        return filterCustomerId;
    }

    public void setFilterCustomerId(Integer filterCustomerId) {
        this.filterCustomerId = filterCustomerId;
    }

    // Add filter method
    public void applyFilters() {
        try {
            Collection<Tblorder> allOrders = user_bean.getAllOrders();
            filteredOrders = new ArrayList<>();
            
            // Debug prints for filter values
            System.out.println("Applying filters:");
            System.out.println("Status filter: " + filterStatus);
            System.out.println("Customer ID filter: " + filterCustomerId);
            
            for (Tblorder order : allOrders) {
                boolean matches = true;
                
                // Filter by status
                if (filterStatus != null && !filterStatus.isEmpty() && !filterStatus.equals("all")) {
                    matches = order.getStatus().equals(filterStatus);
                    System.out.println("Status check for order " + order.getOrderId() + 
                                     ": " + order.getStatus() + " equals " + filterStatus + 
                                     " = " + matches);
                }
                
                // Filter by customer ID
                if (filterCustomerId != null && filterCustomerId > 0) {
                    matches = matches && order.getCustomerId().getCustomerId() == filterCustomerId;
                    System.out.println("Customer ID check for order " + order.getOrderId() + 
                                     ": " + order.getCustomerId().getCustomerId() + 
                                     " equals " + filterCustomerId + " = " + matches);
                }
                
                // Add order to filtered list if it matches all criteria
                if (matches) {
                    filteredOrders.add(order);
                    System.out.println("Added matching order: ID=" + order.getOrderId() + 
                                     ", Status=" + order.getStatus() + 
                                     ", Customer ID=" + order.getCustomerId().getCustomerId());
                }
            }
            
            System.out.println("Filter complete. Found " + filteredOrders.size() + " matching orders");
            
            if (filteredOrders.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "No orders found matching the selected criteria.", null));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error applying filters: " + e.getMessage(), null));
        }
    }
}
