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
@Table(name = "tblservice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblservice.findAll", query = "SELECT t FROM Tblservice t"),
    @NamedQuery(name = "Tblservice.findByServicesId", query = "SELECT t FROM Tblservice t WHERE t.servicesId = :servicesId"),
    @NamedQuery(name = "Tblservice.findByServiceType", query = "SELECT t FROM Tblservice t WHERE t.serviceType = :serviceType"),
    @NamedQuery(name = "Tblservice.findByCharge", query = "SELECT t FROM Tblservice t WHERE t.charge = :charge")})
public class Tblservice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "services_id")
    private Integer servicesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "service_type")
    private String serviceType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "charge")
    private int charge;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceId")
    private Collection<Tblorderitem> tblorderitemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicesId")
    private Collection<Tblemployee> tblemployeeCollection;

    public Tblservice() {
    }

    public Tblservice(Integer servicesId) {
        this.servicesId = servicesId;
    }

    public Tblservice(Integer servicesId, String serviceType, int charge) {
        this.servicesId = servicesId;
        this.serviceType = serviceType;
        this.charge = charge;
    }

    public Integer getServicesId() {
        return servicesId;
    }

    public void setServicesId(Integer servicesId) {
        this.servicesId = servicesId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    @JsonbTransient
    public Collection<Tblorderitem> getTblorderitemCollection() {
        return tblorderitemCollection;
    }

    public void setTblorderitemCollection(Collection<Tblorderitem> tblorderitemCollection) {
        this.tblorderitemCollection = tblorderitemCollection;
    }

    @JsonbTransient
    public Collection<Tblemployee> getTblemployeeCollection() {
        return tblemployeeCollection;
    }

    public void setTblemployeeCollection(Collection<Tblemployee> tblemployeeCollection) {
        this.tblemployeeCollection = tblemployeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicesId != null ? servicesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblservice)) {
            return false;
        }
        Tblservice other = (Tblservice) object;
        if ((this.servicesId == null && other.servicesId != null) || (this.servicesId != null && !this.servicesId.equals(other.servicesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tblservice[ servicesId=" + servicesId + " ]";
    }
    
}
