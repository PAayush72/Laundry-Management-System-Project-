/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
@Named(value = "signin")
@SessionScoped
public class signin implements Serializable {

    /**
     * Creates a new instance of signin
     */
    public signin() {
    }
    
}
