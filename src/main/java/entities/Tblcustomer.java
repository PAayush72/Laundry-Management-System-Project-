/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "tblcustomer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblcustomer.findAll", query = "SELECT t FROM Tblcustomer t"),
    @NamedQuery(name = "Tblcustomer.findByCustomerId", query = "SELECT t FROM Tblcustomer t WHERE t.customerId = :customerId"),
    @NamedQuery(name = "Tblcustomer.findByCustomerName", query = "SELECT t FROM Tblcustomer t WHERE t.customerName = :customerName"),
    @NamedQuery(name = "Tblcustomer.findByCustomerAddress", query = "SELECT t FROM Tblcustomer t WHERE t.customerAddress = :customerAddress"),
    @NamedQuery(name = "Tblcustomer.findByEmail", query = "SELECT t FROM Tblcustomer t WHERE t.email = :email"),
    @NamedQuery(name = "Tblcustomer.findByPhno", query = "SELECT t FROM Tblcustomer t WHERE t.phno = :phno"),
    @NamedQuery(name = "Tblcustomer.findByPassword", query = "SELECT t FROM Tblcustomer t WHERE t.password = :password")})
public class Tblcustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_id")
    private Integer customerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "customer_name")
    private String customerName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "customer_address")
    private String customerAddress;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "phno")
    private String phno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private Tblrole roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Tblorder> tblorderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Tblorderitem> tblorderitemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Tblpayment> tblpaymentCollection;

    public Tblcustomer() {
    }

    public Tblcustomer(Integer customerId) {
        this.customerId = customerId;
    }

    public Tblcustomer(Integer customerId, String customerName, String customerAddress, String email, String phno, String password) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.email = email;
        this.phno = phno;
        this.password = password;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Tblrole getRoleId() {
        return roleId;
    }

    public void setRoleId(Tblrole roleId) {
        this.roleId = roleId;
    }

    @JsonbTransient
    public Collection<Tblorder> getTblorderCollection() {
        return tblorderCollection;
    }

    public void setTblorderCollection(Collection<Tblorder> tblorderCollection) {
        this.tblorderCollection = tblorderCollection;
    }

    @JsonbTransient
    public Collection<Tblorderitem> getTblorderitemCollection() {
        return tblorderitemCollection;
    }

    public void setTblorderitemCollection(Collection<Tblorderitem> tblorderitemCollection) {
        this.tblorderitemCollection = tblorderitemCollection;
    }

    @JsonbTransient
    public Collection<Tblpayment> getTblpaymentCollection() {
        return tblpaymentCollection;
    }

    public void setTblpaymentCollection(Collection<Tblpayment> tblpaymentCollection) {
        this.tblpaymentCollection = tblpaymentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblcustomer)) {
            return false;
        }
        Tblcustomer other = (Tblcustomer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tblcustomer[ customerId=" + customerId + " ]";
    }
    
}
