/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entities.Tblemployee;
import entities.Tblservice;
import java.util.Collection;
//import entity.Tblservice;
//import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO
 */
@Stateless
public class admin_beans implements admin_beansLocal {

    @PersistenceContext(unitName = "project_unit")
    EntityManager em;

    @Override
    public void addservice(String service_type, int charge) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblservice service = new Tblservice();

        service.setServiceType(service_type);
        service.setCharge(charge);

        em.persist(service);

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void addEmployee(String emp_name, int services_id, int salary, String emp_address, String emp_phono) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Tblservice service = (Tblservice) em.find(Tblservice.class, services_id);
        Collection<Tblemployee> Emp = service.getTblemployeeCollection();
        Tblemployee emp = new Tblemployee();
//        emp.setEmpId(11);
        emp.setEmpName(emp_name);
        emp.setServicesId(service);
        emp.setSalary(salary);
        emp.setEmpAddress(emp_address);
        emp.setEmpPhono(emp_phono);
        Emp.add(emp);
        service.setTblemployeeCollection(Emp);
        em.persist(emp);
        em.merge(service);

    }

    @Override
    public void updateEmployee(int emp_id, String emp_name, int services_id, int salary, String emp_address, String emp_phono) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblservice s = (Tblservice) em.find(Tblservice.class, services_id);
        Tblemployee e = (Tblemployee) em.find(Tblemployee.class, emp_id);

        e.setEmpName(emp_name);
        e.setSalary(salary);
        e.setServicesId(s);

        em.merge(e);

    }

    @Override
    public void removeEmployee(int emp_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblemployee e = (Tblemployee) em.find(Tblemployee.class, emp_id);
        em.remove(e);
    }

    @Override
    public Collection<Tblemployee> getAllEmployees() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblemployee.findAll").getResultList();
    }

    @Override
    public Collection<Tblemployee> getEmployeeByServiceId(int services_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        return em.createNamedQuery("Tble")
//                .setParameter("customer_id", services_id)
//                .getResultList();
        Tblservice s = (Tblservice) em.find(Tblservice.class, services_id);
        return s.getTblemployeeCollection();
    }

    @Override
    public Collection<Tblemployee> getEmployeeBySalary(int salary) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblemployee.findBySalary")
                .setParameter("salary", salary)
                .getResultList();
    }

    @Override
    public Collection<Tblemployee> getEmployeeById(int emp_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblemployee.findByEmpId")
                .setParameter("emp_id", emp_id)
                .getResultList();
    }

    @Override
    public Collection<Tblemployee> getEmployeesByName(String emp_name) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblemployee.findByEmpName")
                .setParameter("emp_id", emp_name)
                .getResultList();
    }

    @Override
    public void updateservice(int services_id, String service_type, int charge) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblservice e = (Tblservice) em.find(Tblservice.class, services_id);

        e.setServiceType(service_type);
        e.setCharge(charge);
//        e.setServicesId(s);
        em.merge(e);
    }

    @Override
    public void deleteservice(int services_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Tblservice e = (Tblservice) em.find(Tblservice.class, services_id);
        em.remove(e);
    }

    @Override
    public Collection<Tblservice> getAllServices() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblservice.findAll").getResultList();
    }

    @Override
    public Collection<Tblservice> getServicesByType(String service_type) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblservice.findByServiceType")
                .setParameter("serviceType", service_type)
                .getResultList();
    }

    @Override
    public Collection<Tblservice> getServicesByCharge(int charge) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblservice.findByCharge")
                .setParameter("charge", charge)
                .getResultList();
    }

    @Override
    public Collection<Tblservice> getAllServiceById(int services_id) {
        return em.createNamedQuery("Tblservice.findByServicesId")
                .setParameter("servicesId", services_id)
                .getResultList();
//       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Tblemployee> getEmployeesByPhone(String emp_phono) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblemployee.findByEmpPhono")
                .setParameter("empPhono", emp_phono)
                .getResultList();
    }

    @Override
    public Collection<Tblemployee> getEmployeesByAddress(String emp_address) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Tblemployee.findByEmpAddress")
                .setParameter("empAddress", emp_address)
                .getResultList();
    }

}
