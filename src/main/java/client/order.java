/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:OrderResource [order]<br>
 * USAGE:
 * <pre>
 *        order client = new order();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author LENOVO
 */
public class order {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/laundrySystem/resources";

    public order() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("order");
    }

    public <T> T getAllOrderitemByServiceId(Class<T> responseType, String service_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllOrderitemByServiceId/{0}", new Object[]{service_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getOrderById(Class<T> responseType, String order_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getOrderById/{0}", new Object[]{order_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getOrderByDelivery_Date(Class<T> responseType, String delivery_date) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getOrderByDelivery_Date/{0}", new Object[]{delivery_date}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateOrderItem(String order_item_id, String services_id, String order_id, String material, String qty, String photo) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateOrderItem/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{order_item_id, services_id, order_id, material, qty, photo})).request().put(null);
    }

    public <T> T getAllOrderitemByMaterial(Class<T> responseType, String material) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllOrderitemByMaterial/{0}", new Object[]{material}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getOrderByStatus(Class<T> responseType, String status) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getOrderByStatus/{0}", new Object[]{status}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllOrderitem(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllOrderitem");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response updateOrder(String order_id, String customer_id, String pickup_date, String delivery_date, String status) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdateOrder/{0}/{1}/{2}/{3}/{4}", new Object[]{order_id, customer_id, pickup_date, delivery_date, status})).request().put(null, Response.class);
    }

    public <T> T getAllOrderitemByOrderId(Class<T> responseType, String order_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllOrderitemByOrderId/{0}", new Object[]{order_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getOrderByPickup_Date(Class<T> responseType, String pickup_date) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getOrderByPickup_Date/{0}", new Object[]{pickup_date}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllOrders(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllOrders");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getOrderByOrderDate(Class<T> responseType, String order_date) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getorderdate/{0}", new Object[]{order_date}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllOrderitemById(Class<T> responseType, String order_item_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllOrderitemById/{0}", new Object[]{order_item_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteOrderItem(String order_item_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteOrderItem/{0}", new Object[]{order_item_id})).request().delete();
    }

    public Response addorderItem(String service_id, String order_id, String material, String qty, String photo) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addorderitem/{0}/{1}/{2}/{3}/{4}", new Object[]{service_id, order_id, material, qty, photo})).request().post(null, Response.class);
    }

    public void addorder(String customer_id, String pickup_date, String delivery_date, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addorder/{0}/{1}/{2}/{3}", new Object[]{customer_id, pickup_date, delivery_date, status})).request().post(null);
    }

    public void deleteOrder(String order_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeleteOrder/{0}", new Object[]{order_id})).request().delete();
    }

    public <T> T getOrderByCustomerId(Class<T> responseType, String customer_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getOrderByCustomerId/{0}", new Object[]{customer_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
