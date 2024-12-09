/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author LENOVO
 */
@Named(value = "logout")
@SessionScoped
public class logout implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of logout
     * @return 
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.jsf?faces-redirect=true"; // Redirect to login pag
    }
}
