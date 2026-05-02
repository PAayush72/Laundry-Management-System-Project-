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
@Table(name = "tblrole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblrole.findAll", query = "SELECT t FROM Tblrole t"),
    @NamedQuery(name = "Tblrole.findByRoleId", query = "SELECT t FROM Tblrole t WHERE t.roleId = :roleId"),
    @NamedQuery(name = "Tblrole.findByRoleName", query = "SELECT t FROM Tblrole t WHERE t.roleName = :roleName")})
public class Tblrole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id")
    private Integer roleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<Tblcustomer> tblcustomerCollection;

    public Tblrole() {
    }

    public Tblrole(Integer roleId) {
        this.roleId = roleId;
    }

    public Tblrole(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @JsonbTransient
    public Collection<Tblcustomer> getTblcustomerCollection() {
        return tblcustomerCollection;
    }

    public void setTblcustomerCollection(Collection<Tblcustomer> tblcustomerCollection) {
        this.tblcustomerCollection = tblcustomerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblrole)) {
            return false;
        }
        Tblrole other = (Tblrole) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tblrole[ roleId=" + roleId + " ]";
    }
    
}
