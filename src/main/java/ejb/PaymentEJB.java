package ejb;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PaymentEJB {

   @Inject
   private RazorpayService razorpayService;

   /**
    * Create a Razorpay order by calling the RazorpayEJB.
    *
    * @param amount The total amount in INR (without paise).
    * @return The Razorpay Order ID.
    */
   public String createRazorpayOrder(int amount) {
       try {
           // Call RazorpayEJB to create the order
           Order order = razorpayService.createOrder(amount);
           return order.get("id").toString();  // Return the order ID
       } catch (RazorpayException e) {
           e.printStackTrace();
           return null;
       }
   }

   /**
    * Verify the Razorpay payment using the payment ID and signature.
    *
    * @param paymentId Razorpay payment ID.
    * @param signature Razorpay payment signature.
    * @return true if the payment is valid, false otherwise.
    */
   public boolean verifyPayment(String paymentId, String signature) {
       try {
           RazorpayClient razorpayClient = new RazorpayClient("YOUR_API_KEY", "YOUR_API_SECRET");
           
           // Fetch the payment details from Razorpay
           Payment payment = razorpayClient.payments.fetch(paymentId);

           // Verify the signature using Razorpay's SDK (this is done on the backend)
           boolean isSignatureValid = verifySignature(payment, signature);
           return isSignatureValid;
       } catch (RazorpayException e) {
           e.printStackTrace();
           return false;
       }
   }

   private boolean verifySignature(Payment payment, String signature) {
       // Logic to verify signature, using Razorpay's SDK (details in their documentation)
       // Note: Razorpay's SDK provides a method for signature verification
       String generatedSignature = payment.get("razorpay_signature").toString();
       return generatedSignature.equals(signature);
   }
}
