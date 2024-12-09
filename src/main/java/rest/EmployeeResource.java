/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.admin_beansLocal;
import entities.Tblemployee;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author LENOVO
 */
@Path("Employee")
@RequestScoped
public class EmployeeResource {

    @EJB
    admin_beansLocal u;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }

    @POST
    @Path("/addEmployee/{emp_name}/{services_id}/{salary}")
    public void addEmployee(@PathParam("emp_name") String emp_name,
            @PathParam("services_id") int services_id,
            @PathParam("salary") int salary,
            @PathParam("emp_address") String emp_address,
            @PathParam("emp_phono") String emp_phono) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.addEmployee(emp_name, services_id, salary, emp_address, emp_phono);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateEmployee/{emp_id}/{emp_name}/{services_id}/{salary}")
    public void updateEmployee(@PathParam("emp_id") int emp_id,
            @PathParam("emp_name") String emp_name,
            @PathParam("services_id") int services_id,
            @PathParam("salary") int salary,
            @PathParam("emp_address") String emp_address,
            @PathParam("emp_phono") String emp_phono) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.updateEmployee(emp_id, emp_name, services_id, salary, emp_address, emp_phono);
    }

    @DELETE
    @Path("/removeEmployee/{emp_id}")
    public void removeEmployee(@PathParam("emp_id") int emp_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        u.removeEmployee(emp_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllEmployees/")
    public Collection<Tblemployee> getAllEmployees() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getAllEmployees();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEmployeeByServiceId/{services_id}")
    public Collection<Tblemployee> getEmployeeByServiceId(@PathParam("services_id") int services_id) {
        return u.getEmployeeByServiceId(services_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEmployeeBySalary/{salary}")
    public Collection<Tblemployee> getEmployeeBySalary(@PathParam("salary") int salary) {
        return u.getEmployeeBySalary(salary);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEmployeeById/{emp_id}")
    public Collection<Tblemployee> getEmployeeById(@PathParam("emp_id") int emp_id) {
        return u.getEmployeeById(emp_id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEmployeesByName/{emp_name}")
    public Collection<Tblemployee> getEmployeesByName(@PathParam("emp_name") String emp_name) {
        return u.getEmployeesByName(emp_name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEmployeesByPhone/{emp_phono}")
    public Collection<Tblemployee> getEmployeesByPhone(String emp_phono) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getEmployeesByAddress(emp_phono);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEmployeesByAddress/{emp_address}")
    public Collection<Tblemployee> getEmployeesByAddress(String emp_address) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return u.getEmployeesByPhone(emp_address);
    }

}
