/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:EmployeeResource
 * [Employee]<br>
 * USAGE:
 * <pre>
 *        employee client = new employee();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author LENOVO
 */
public class employee {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/laundrySystem/webresources";

    public employee() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Employee");
    }

    public <T> T getEmployeeById(Class<T> responseType, String emp_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getEmployeeById/{0}", new Object[]{emp_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getEmployeeBySalary(Class<T> responseType, String salary) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getEmployeeBySalary/{0}", new Object[]{salary}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getEmployeesByAddress(Class<T> responseType, String emp_address) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getEmployeesByAddress/{0}", new Object[]{emp_address}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getEmployeesByPhone(Class<T> responseType, String emp_phono) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getEmployeesByPhone/{0}", new Object[]{emp_phono}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getEmployeesByName(Class<T> responseType, String emp_name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getEmployeesByName/{0}", new Object[]{emp_name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateEmployee(Object requestEntity, String emp_id, String emp_name, String services_id, String salary) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateEmployee/{0}/{1}/{2}/{3}", new Object[]{emp_id, emp_name, services_id, salary})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeEmployee(String emp_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeEmployee/{0}", new Object[]{emp_id})).request().delete();
    }

    public <T> T getEmployeeByServiceId(Class<T> responseType, String services_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getEmployeeByServiceId/{0}", new Object[]{services_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addEmployee(String emp_name, String services_id, String salary) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addEmployee/{0}/{1}/{2}", new Object[]{emp_name, services_id, salary})).request().post(null);
    }

    public <T> T getAllEmployees(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllEmployees");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
