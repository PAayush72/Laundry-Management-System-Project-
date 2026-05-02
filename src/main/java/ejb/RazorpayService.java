package ejb;

import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import javax.ejb.Stateless;

@Stateless
public class RazorpayService {  // Ensure class name matches the filename

    private static final String API_KEY = "rzp_test_VWZZ4Jn0Rd1lpQ";  // Replace with your Razorpay test key
    private static final String API_SECRET = "gTXJvz2m0YS3mlksdynKGgr7";  // Replace with your Razorpay test secret

    /**
     * Create an order on the Razorpay server.
     *
     * @param amount The amount in INR (without paise).
     * @return Razorpay Order object
     * @throws RazorpayException If there's an error creating the order
     */
    public Order createOrder(int amount) throws RazorpayException {
        amount *= 100;  // Convert amount to paise (1 INR = 100 paise)

        try {
            // Initialize RazorpayClient with API credentials
            RazorpayClient razorpayClient = new RazorpayClient(API_KEY, API_SECRET);

            // Prepare the order request
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount);
            orderRequest.put("currency", "INR");
            orderRequest.put("payment_capture", 1);  // Auto capture after payment

            // Create the order and return the order object
            return razorpayClient.orders.create(orderRequest);
        } catch (RazorpayException e) {
            System.err.println("Error creating Razorpay order: " + e.getMessage());
            throw e;  // Rethrow the exception
        }
    }
}
