/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ServiceResource [Service]<br>
 * USAGE:
 * <pre>
 *        services client = new services();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author LENOVO
 */
public class services {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/laundrySystem/resources";

    public services() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Service");
    }

    public <T> T getAllServices(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllServices");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getServicesByCharge(Class<T> responseType, String charge) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getServicesByCharge/{0}", new Object[]{charge}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllServiceById(Class<T> responseType, String services_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllServiceById/{0}", new Object[]{services_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addservice(String service_type, String charge) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addservice/{0}/{1}", new Object[]{service_type, charge})).request().post(null);
    }

    public void updateservice(Object requestEntity, String services_id, String service_type, String charge) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateservice/{0}/{1}/{2}", new Object[]{services_id, service_type, charge})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void deleteservice(String services_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteservice/{0}", new Object[]{services_id})).request().delete();
    }

    public <T> T getServicesByType(Class<T> responseType, String service_type) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getServicesByType/{0}", new Object[]{service_type}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
