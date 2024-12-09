/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vanshita
 */
@Named(value = "loginMB")
@RequestScoped
public class LoginMB {

    @Inject
    private SecurityContext securityContext;

//    @EJB
//    private UserBeanLocal userBean;
//    
//    private Users newUser = new Users();
//    private Integer roleId;
    private String email;
    private String password;
//    private String confirmPassword;
    private String message;
    private AuthenticationStatus status;
    private Set<String> roles;

    /**
     * Creates a new instance of UserMB
     */
    public LoginMB() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            request.getSession().setAttribute("logged-group", "");

            Credential credential = new UsernamePasswordCredential(email, new Password(password));
            AuthenticationStatus status = securityContext.authenticate(request, response, withParams().credential(credential));

            System.out.println("Status in bean : " + status);

            if (status.equals(SEND_CONTINUE)) {
                // Authentication mechanism has send a redirect, should not
                // send anything to response from JSF now. The control will now go into HttpAuthenticationMechanism
                context.responseComplete();
            }

            System.out.println("In bean Roles :" + roles);
            if (roles.contains("admin")) {
                System.out.println("In admin");
                request.getSession().setAttribute("logged-group", "admin");
                return "index.jsf?faces-redirect=true";
            } //   else if(securityContext.isCallerInRole("Supervisor"))
            else if (roles.contains("user") || roles.contains("employee")) {
                System.out.println("In user");
                if (roles.contains("user")) {
                    request.getSession().setAttribute("logged-group", "user");
                    request.getSession().setAttribute("user-email", email);
//                  request.getSession().setAttribute("user-email", email);
                } else if (roles.contains("employee")) {
                    request.getSession().setAttribute("logged-group", "employee");
                    request.getSession().setAttribute("user-email", email);
                }
                return "index.jsf?faces-redirect=true";
            }
//            else if (roles.contains("free-user")) {
//                System.out.println("In user");
//                request.getSession().setAttribute("logged-group", "free-user");
//                return "/user_side/dashboard.jsf?faces-redirect=true";
//            }

            //} 
        } catch (Exception e) {
            System.out.print("Username pass wrong");
            message = "Error : Username or Password is Incorrect!!!";
            //   e.printStackTrace();
        }
//        
        return "error.jsf?faces-redirect=true";
    }

    private static void addError(FacesContext context, String message) {
        context = FacesContext.getCurrentInstance();
        context.addMessage(
                null,
                new FacesMessage(SEVERITY_ERROR, message, null));
    }

    public String logout() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().setAttribute("logged-group", "");
        request.logout();
        request.getSession().invalidate();

        return "/login.jsf?faces-redirect=true";

    }

    public void navigateToLoginPage() throws IOException {
        // Redirect to the register page
        FacesContext.getCurrentInstance().getExternalContext().redirect("../login.jsf");
    }

    public void navigateToRegisterPage() throws IOException {
        // Redirect to the register page
        FacesContext.getCurrentInstance().getExternalContext().redirect("../login.jsf");
    }

}
