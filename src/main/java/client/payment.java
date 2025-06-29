/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:PaymentResource [Payment]<br>
 * USAGE:
 * <pre>
 *        payment client = new payment();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author LENOVO
 */
public class payment {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/laundrySystem/resources";

    public payment() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Payment");
    }

    public void updatepayment(String pay_id, String customer_id, String order_id, String amount, String method) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatepayment/{0}/{1}/{2}/{3}/{4}", new Object[]{pay_id, customer_id, order_id, amount, method})).request().put(null);
    }

    public void addpayment(String customer_id, String order_id, String amount, String method) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addpayment/{0}/{1}/{2}/{3}", new Object[]{customer_id, order_id, amount, method})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
