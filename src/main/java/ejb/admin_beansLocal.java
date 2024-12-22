/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

//import java.util.Date;
import entities.Tblemployee;
import entities.Tblservice;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author LENOVO
 */
@Local
public interface admin_beansLocal {

    void addservice(String service_type, int charge);

    void updateservice(int services_id, String service_type, int charge);

    void deleteservice(int services_id);

    Tblservice getAllServiceById(int services_id);

    Collection<Tblservice> getAllServices();

    Collection<Tblservice> getServicesByType(String service_type);

    Collection<Tblservice> getServicesByCharge(int charge);

    //    Collection<Tblservice> getOrderByServiceId(int customer_id);
    void addEmployee(String emp_name, int services_id, int salary, String emp_address, String emp_phono);

    void updateEmployee(int emp_id, String emp_name, int services_id, int salary, String emp_address, String emp_phono);

    void removeEmployee(int emp_id);

    Collection<Tblemployee> getAllEmployees();

    Collection<Tblemployee> getEmployeeByServiceId(int services_id);

    Collection<Tblemployee> getEmployeeBySalary(int salary);

    Collection<Tblemployee> getEmployeeById(int emp_id);

    Collection<Tblemployee> getEmployeesByName(String emp_name);

    Collection<Tblemployee> getEmployeesByPhone(String emp_phono);

    Collection<Tblemployee> getEmployeesByAddress(String emp_address);

//    void addorder(int customer_id,Date order_date,Date pickup_date,Date delivery_date,String status);
}
