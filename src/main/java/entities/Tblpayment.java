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
@Table(name = "tblpayment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblpayment.findAll", query = "SELECT t FROM Tblpayment t"),
    @NamedQuery(name = "Tblpayment.findByPayId", query = "SELECT t FROM Tblpayment t WHERE t.payId = :payId"),
    @NamedQuery(name = "Tblpayment.findByAmount", query = "SELECT t FROM Tblpayment t WHERE t.amount = :amount"),
    @NamedQuery(name = "Tblpayment.findByMethod", query = "SELECT t FROM Tblpayment t WHERE t.method = :method")})
public class Tblpayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pay_id")
    private Integer payId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "method")
    private String method;
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Tblcustomer customerId;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private Tblorder orderId;

    public Tblpayment() {
    }

    public Tblpayment(Integer payId) {
        this.payId = payId;
    }

    public Tblpayment(Integer payId, int amount, String method) {
        this.payId = payId;
        this.amount = amount;
        this.method = method;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Tblcustomer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Tblcustomer customerId) {
        this.customerId = customerId;
    }

    public Tblorder getOrderId() {
        return orderId;
    }

    public void setOrderId(Tblorder orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payId != null ? payId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblpayment)) {
            return false;
        }
        Tblpayment other = (Tblpayment) object;
        if ((this.payId == null && other.payId != null) || (this.payId != null && !this.payId.equals(other.payId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tblpayment[ payId=" + payId + " ]";
    }
    
}
