/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CustomerResource
 * [customer]<br>
 * USAGE:
 * <pre>
 *        customer client = new customer();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author LENOVO
 */
public class customer {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/laundrySystem/resources";

    public customer() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("customer");
    }

    public void addCustomer(String customer_name, String customer_address, String email, String phno, String password, String role_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addcust/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{customer_name, customer_address, email, phno, password, role_id})).request().post(null);
    }

    public String sayHello() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public <T> T getAllCustomers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("user/all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllCustomersByName(Class<T> responseType, String customer_name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getcustbyname/{0}", new Object[]{customer_name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateCustomer(String customer_id, String customer_name, String customer_address, String email, String phno, String password) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatecust/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{customer_id, customer_name, customer_address, email, phno, password})).request().put(null);
    }

    public <T> T getCustomersByEmail(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCustByEmail/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeCustomer(String customer_id, String role_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeCust/{0}/{1}", new Object[]{customer_id, role_id})).request().delete();
    }

    public <T> T getCustomersById(Class<T> responseType, String customer_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCustById/{0}", new Object[]{customer_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getCustomersByPhno(Class<T> responseType, String phno) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCustByPhno/{0}", new Object[]{phno}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllCustomersByAddress(Class<T> responseType, String customer_address) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllCustByAddress/{0}", new Object[]{customer_address}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
