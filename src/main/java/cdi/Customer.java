package cdi;

import ejb.user_beanLocal;
import entities.Tblcustomer;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "customer")
@RequestScoped
public class Customer implements Serializable {

    @Inject
    private user_beanLocal ubl;

    private Collection<Tblcustomer> cust;

    @PostConstruct
    public void init() {
        cust = ubl.getAllCustomers();
    }

    public Collection<Tblcustomer> getCustomers() {
        return cust;
    }

    public void setCustomers(Collection<Tblcustomer> customers) {
        this.cust = customers;
    }
}
