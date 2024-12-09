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
@Table(name = "tblorderitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblorderitem.findAll", query = "SELECT t FROM Tblorderitem t"),
    @NamedQuery(name = "Tblorderitem.findByOrderItemId", query = "SELECT t FROM Tblorderitem t WHERE t.orderItemId = :orderItemId"),
    @NamedQuery(name = "Tblorderitem.findByMaterial", query = "SELECT t FROM Tblorderitem t WHERE t.material = :material"),
    @NamedQuery(name = "Tblorderitem.findByQty", query = "SELECT t FROM Tblorderitem t WHERE t.qty = :qty"),
    @NamedQuery(name = "Tblorderitem.findByPhoto", query = "SELECT t FROM Tblorderitem t WHERE t.photo = :photo")})
public class Tblorderitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_item_id")
    private Integer orderItemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "material")
    private String material;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private int qty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "photo")
    private String photo;
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Tblcustomer customerId;
    @JoinColumn(name = "service_id", referencedColumnName = "services_id")
    @ManyToOne(optional = false)
    private Tblservice serviceId;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private Tblorder orderId;

    public Tblorderitem() {
    }

    public Tblorderitem(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Tblorderitem(Integer orderItemId, String material, int qty, String photo) {
        this.orderItemId = orderItemId;
        this.material = material;
        this.qty = qty;
        this.photo = photo;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Tblcustomer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Tblcustomer customerId) {
        this.customerId = customerId;
    }

    public Tblservice getServiceId() {
        return serviceId;
    }

    public void setServiceId(Tblservice serviceId) {
        this.serviceId = serviceId;
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
        hash += (orderItemId != null ? orderItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblorderitem)) {
            return false;
        }
        Tblorderitem other = (Tblorderitem) object;
        if ((this.orderItemId == null && other.orderItemId != null) || (this.orderItemId != null && !this.orderItemId.equals(other.orderItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tblorderitem[ orderItemId=" + orderItemId + " ]";
    }
    
}
