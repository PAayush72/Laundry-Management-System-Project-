/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "tblorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblorder.findAll", query = "SELECT t FROM Tblorder t"),
    @NamedQuery(name = "Tblorder.findByOrderId", query = "SELECT t FROM Tblorder t WHERE t.orderId = :orderId"),
    @NamedQuery(name = "Tblorder.findByOrderDate", query = "SELECT t FROM Tblorder t WHERE t.orderDate = :orderDate"),
    @NamedQuery(name = "Tblorder.findByPickupDate", query = "SELECT t FROM Tblorder t WHERE t.pickupDate = :pickupDate"),
    @NamedQuery(name = "Tblorder.findByDeliveryDate", query = "SELECT t FROM Tblorder t WHERE t.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "Tblorder.findByStatus", query = "SELECT t FROM Tblorder t WHERE t.status = :status")})
public class Tblorder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pickup_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickupDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Tblcustomer customerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<Tblorderitem> tblorderitemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<Tblpayment> tblpaymentCollection;

    public Tblorder() {
    }

    public Tblorder(Integer orderId) {
        this.orderId = orderId;
    }

    public Tblorder(Integer orderId, Date orderDate, Date pickupDate, Date deliveryDate, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.pickupDate = pickupDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tblcustomer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Tblcustomer customerId) {
        this.customerId = customerId;
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
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblorder)) {
            return false;
        }
        Tblorder other = (Tblorder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tblorder[ orderId=" + orderId + " ]";
    }
    
}
