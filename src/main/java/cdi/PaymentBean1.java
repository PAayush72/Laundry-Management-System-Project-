package cdi;

import ejb.PaymentEJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "paymentBean")
@RequestScoped
public class PaymentBean1 {

    @Inject
    private PaymentEJB paymentEJB;  // Inject the PaymentEJB for order creation

    private int totalAmount;
    private String orderId;
    private String paymentStatusMessage;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String createOrder() {
        System.out.println(totalAmount);
        // Call PaymentEJB to create the Razorpay order
        orderId = paymentEJB.createRazorpayOrder(totalAmount);
        if (orderId != null) {
            return "paymentPage.xhtml";  // Redirect to payment page
        }
        return "errorPage";  // If there was an error, show error page
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void onPaymentSuccess(String razorpayPaymentId, String razorpaySignature) {
        boolean isPaymentValid = paymentEJB.verifyPayment(razorpayPaymentId, razorpaySignature);

        if (isPaymentValid) {
            this.paymentStatusMessage = "Payment Successful!";
        } else {
            this.paymentStatusMessage = "Payment verification failed.";
        }
    }

    public String getPaymentStatusMessage() {
        return paymentStatusMessage;
    }

    public void setPaymentStatusMessage(String paymentStatusMessage) {
        this.paymentStatusMessage = paymentStatusMessage;
    }
}
