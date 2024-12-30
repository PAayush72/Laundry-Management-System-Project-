package cdi;

import client.order;
import ejb.PaymentEJB;
import ejb.admin_beansLocal;
import ejb.user_beanLocal;
import entities.Tblorder;
import entities.Tblorderitem;
import entities.Tblpayment;
import entities.Tblservice;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import mail.mail;
import org.primefaces.model.file.UploadedFile;
import service.CloudinaryService;

@Named(value = "orderItemBean")
@ViewScoped
public class orderItemBean implements Serializable {

    @Inject
    private PaymentEJB paymentEJB;
    @Inject
    private mail emailSender;
    @EJB
    private user_beanLocal user_bean;

    @EJB
    private admin_beansLocal admin_beans;

    private static final Logger LOGGER = Logger.getLogger(orderItemBean.class.getName());
    private int orderItemId;
    private Tblorderitem itm;
    private order o;
    private Response rs;

    private List<Tblorderitem> ordItem1;
    private Collection<Tblorderitem> ordItem;
    private GenericType<Collection<Tblorderitem>> gordItem;
    private int customerId;
    private int orderId;
    private String order_Id;

    private int serviceId;
    private String material;
    private int qty;
    private Part photo;
    private String img;
    private UploadedFile image;
    private String status;
    private Tblorder order;
    private int servicesId;
    private double charge;
    private double amount;
    private String userEmail;
    @Inject
    private LoginMB loginMB;
    @Inject
    private CloudinaryService cloudinary;

    @PostConstruct
    public void init() {
        this.userEmail = loginMB.getC().getEmail();
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (facesContext == null) {
                System.out.println("FacesContext is null.");
                return;
            }

            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            if (session == null) {
                System.out.println("HttpSession is null.");
                return;
            }

            // Retrieve orderItemId from session
            Integer orderItemId = (Integer) session.getAttribute("orderItemId");
            if (orderItemId != null) {
                System.out.println("Order Item ID retrieved from session: " + orderItemId);
                this.orderItemId = orderItemId;
            } else {
                System.out.println("Order Item ID is not available in session.");
            }

            // Retrieve the Tblorder object from session
            Tblorder latestOrder = (Tblorder) session.getAttribute("orderBean");
            if (latestOrder != null) {
                this.orderId = latestOrder.getOrderId();
                System.out.println("Order ID retrieved from session: " + orderId);
            } else {
                System.out.println("Order (orderBean) is not available in session.");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Prints the exception stack trace to the console
        }

        // Parse orderId from request parameter if available
        FacesContext context = FacesContext.getCurrentInstance();
        String orderIdString = context.getExternalContext().getRequestParameterMap().get("orderId");
        if (orderIdString != null) {
            try {
                this.orderId = Integer.parseInt(orderIdString);
                System.out.println("Order ID parsed from request: " + orderId);
                loadUserDetails();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Handle update of orderItemId
        String updateOrder = context.getExternalContext().getRequestParameterMap().get("orderId");
        String updateOrderItem = context.getExternalContext().getRequestParameterMap().get("orderItemId");
        if (updateOrder != null && updateOrderItem != null) {
            this.orderId = Integer.parseInt(updateOrder);
            this.orderItemId = Integer.parseInt(updateOrderItem);
            loadOrderItemDetails(orderId, orderItemId);
        }
    }

    private void loadOrderItemDetails(int oid, int itmId) {
        this.order = user_bean.getOrderById(oid);
        this.itm = user_bean.getAllOrderitemById(itmId);
    }

    public void loadUserDetails() {
        // Placeholder to load user details if needed
    }

    public void addorderItem() {
        System.out.println("Method Triggered");
        System.out.println("Service Id: " + serviceId);

        // Upload image if present
        if (image != null) {
            try {
                img = cloudinary.uploadImage(image);
            } catch (IOException ex) {
                Logger.getLogger(orderItemBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Create and set order item details
        Tblorderitem t = new Tblorderitem();
        Tblorder odr = new Tblorder();
        Tblservice ser = new Tblservice();

        t.setMaterial(material);
        ser.setServicesId(serviceId);
        t.setServiceId(ser);
        t.setQty(qty);
        odr.setOrderId(orderId);
        t.setOrderId(odr);

        // Add order item to the database via user_bean
        user_bean.addorderItem(serviceId, orderId, material, qty, img);

        // Ensure the orderItemId is set before storing in the session
//        this.orderItemId = t.getOrderItemId(); // Set the generated orderItemId here (assumes it gets populated)
        System.out.println("Order Item ID set to: " + this.orderItemId);

        // Store orderItemId in session
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("orderItemId", this.orderItemId);
    }

    public void displayOrderItems(int orderId) {
        this.orderId = orderId;
        ordItem = getAllOrderitemByOrderId(orderId);
    }

    public void updateOrderItem() {
        if (image != null && image.getSize() > 0) {
            try {
                img = cloudinary.uploadImage(image);
            } catch (IOException ex) {
                Logger.getLogger(orderItemBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            img = getExistingImagePath();
        }
        user_bean.updateOrderItem(this.orderItemId, serviceId, this.orderId, material, qty, img);
    }

    private String getExistingImagePath() {
        // Fetch the existing image path from your database or service layer
        // For example:
        Tblorderitem existingItem = user_bean.getAllOrderitemById(this.orderItemId);  // Get order item by ID
        return existingItem != null ? existingItem.getPhoto() : null;  // Return the existing image or null if not found
    }

    public Collection<Tblorderitem> getAllOrderitemByOrderId(int order_Id) {
        return user_bean.getAllOrderitemByOrderId(order_Id);
    }

    public void deleteOrderItem(int order_item_id, int order_id) {
        user_bean.deleteOrderItem(order_item_id, order_id);
    }

    // Getter and Setter methods
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public List<Tblorderitem> getOrdItem1() {
        return ordItem1;
    }

    public void setOrdItem1(List<Tblorderitem> ordItem1) {
        this.ordItem1 = ordItem1;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Collection<Tblorderitem> getOrdItem() {
        return ordItem;
    }

    public void setOrdItem(Collection<Tblorderitem> ordItem) {
        this.ordItem = ordItem;
    }

    public GenericType<Collection<Tblorderitem>> getGordItem() {
        return gordItem;
    }

    public void setGordItem(GenericType<Collection<Tblorderitem>> gordItem) {
        this.gordItem = gordItem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Part getPhoto() {
        return photo;
    }

    public void setPhoto(Part photo) {
        this.photo = photo;
    }

    public order getO() {
        return o;
    }

    public void setO(order o) {
        this.o = o;
    }

    public Response getRs() {
        return rs;
    }

    public void setRs(Response rs) {
        this.rs = rs;
    }

    public Tblorderitem getItm() {
        return itm;
    }

    public void setItm(Tblorderitem itm) {
        this.itm = itm;
    }

    public Tblorder getOrder() {
        return order;
    }

    public void setOrder(Tblorder order) {
        this.order = order;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Tblservice getAllServiceById(int services_id) {
        return admin_beans.getAllServiceById(services_id);

    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    public double calculateGrandTotal(List<Tblorderitem> orderItems) {
        double grandTotal = 0.0;

        // Log the order items to see if they are populated
        if (orderItems != null && !orderItems.isEmpty()) {
            System.out.println("Order Items: " + orderItems.size() + " items found.");
            for (Tblorderitem item : orderItems) {
                // Log the details of each order item for debugging
                System.out.println("Item: " + item.getMaterial() + ", Qty: " + item.getQty());

                Tblservice service = getAllServiceById(item.getServiceId().getServicesId());

                if (service != null) {
                    System.out.println("Service Charge: " + service.getCharge());
                    // Calculate the total for the item (qty * service charge)
                    grandTotal += item.getQty() * service.getCharge();

                } else {
                    System.out.println("Service not found for Item ID: " + item.getServiceId().getServicesId());
                }
            }
        } else {
            System.out.println("No order items found or the list is null.");
        }

        System.out.println("Grand Total: " + grandTotal);
        this.amount = grandTotal;
        return grandTotal;
    }

    public int getCustomerIdFromOrder(int orderId) {
        Tblorder order = user_bean.getOrderById(orderId); // Assuming you have this method in user_bean
        return order.getCustomerId().getCustomerId(); // Assuming there's a relationship between Tblorder and Tblcustomer
    }

    public String processPayment() {
        try {
            Date orderDate = new Date();
            // Update order status to "success"
            user_bean.updateOrderStatus(orderId, "success");

            int customerId = getCustomerIdFromOrder(orderId);

//            double amount1 = calculateGrandTotal(ordItem1);
//            int roundedAmount = (int) Math.round(amount);
            String paymentMethod = "Online Mode";

            // Create and add a payment record
//            System.out.println("Amout1:"+amount1);
            System.out.println("Amout:" + this.amount);
            user_bean.addpayment(customerId, orderId, this.amount, paymentMethod); // Pass all required parameters
            emailSender.sendEmail(userEmail, orderId, orderDate);
            return "displayOrders?faces-redirect=true";
            // Show a success message
//            FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Payment Successful"));

        } catch (Exception e) {
            // Show a failure message
            FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Payment Failed"));
            e.printStackTrace();
            return "order_item_details?faces-redirect=true";
        }
    }

    public Collection<Tblpayment> getAllPaymentDetails() {
        return user_bean.getAllPaymentDetails();
    }

    public orderItemBean() {
        ordItem1 = new ArrayList<>();
        itm = new Tblorderitem();
        o = new order();
        ordItem = new ArrayList<>();
        gordItem = new GenericType<Collection<Tblorderitem>>() {
        };
    }
}
