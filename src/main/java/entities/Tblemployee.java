/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "tblemployee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblemployee.findAll", query = "SELECT t FROM Tblemployee t"),
    @NamedQuery(name = "Tblemployee.findByEmpId", query = "SELECT t FROM Tblemployee t WHERE t.empId = :empId"),
    @NamedQuery(name = "Tblemployee.findByEmpName", query = "SELECT t FROM Tblemployee t WHERE t.empName = :empName"),
    @NamedQuery(name = "Tblemployee.findBySalary", query = "SELECT t FROM Tblemployee t WHERE t.salary = :salary"),
    @NamedQuery(name = "Tblemployee.findByEmpAddress", query = "SELECT t FROM Tblemployee t WHERE t.empAddress = :empAddress"),
    @NamedQuery(name = "Tblemployee.findByEmpPhono", query = "SELECT t FROM Tblemployee t WHERE t.empPhono = :empPhono")})
public class Tblemployee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_id")
    private Integer empId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "emp_name")
    private String empName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private int salary;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "emp_address")
    private String empAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "emp_phono")
    private String empPhono;
    @JoinColumn(name = "services_id", referencedColumnName = "services_id")
    @ManyToOne(optional = false)
    private Tblservice servicesId;

    public Tblemployee() {
    }

    public Tblemployee(Integer empId) {
        this.empId = empId;
    }

    public Tblemployee(Integer empId, String empName, int salary, String empAddress, String empPhono) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.empAddress = empAddress;
        this.empPhono = empPhono;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpPhono() {
        return empPhono;
    }

    public void setEmpPhono(String empPhono) {
        this.empPhono = empPhono;
    }

    public Tblservice getServicesId() {
        return servicesId;
    }

    public void setServicesId(Tblservice servicesId) {
        this.servicesId = servicesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblemployee)) {
            return false;
        }
        Tblemployee other = (Tblemployee) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tblemployee[ empId=" + empId + " ]";
    }
    
}
