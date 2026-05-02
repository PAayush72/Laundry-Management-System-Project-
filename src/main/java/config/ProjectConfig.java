/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author LENOVO
 */
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/laundrypool",
        callerQuery = "SELECT password FROM tblcustomer WHERE email = ?",
        groupsQuery = "SELECT r.role_name FROM tblcustomer c JOIN tblrole r ON c.role_id = r.role_id WHERE c.email = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
)

@Named
@ApplicationScoped
public class ProjectConfig {

    public ProjectConfig() {
        System.out.println("Project Config Initialized");
    }
}
