package cdi;

import client.customer;
import entities.Tblcustomer;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Named(value = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    @Inject
    private SecurityContext securityContext;

    private customer cl;

    private String email;
    private String id;
    private Tblcustomer c;
    private GenericType<Tblcustomer> gc;
    private Response rs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String password;
    private String username;

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Tblcustomer getC() {
        return c;
    }

    public void setC(Tblcustomer c) {
        this.c = c;
    }

    private String message;
    private AuthenticationStatus status;
    private Set<String> roles;

    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_TIME_MINUTES = 1;
    private static final ConcurrentHashMap<String, Integer> failedAttempts = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, LocalDateTime> lockTime = new ConcurrentHashMap<>();

    public int getRemainingAttempts() {
        if (email == null || email.trim().isEmpty()) {
            return MAX_ATTEMPTS;
        }
        return MAX_ATTEMPTS - failedAttempts.getOrDefault(email, 0);
    }

    public LoginMB() {

    }

    @PostConstruct
    public void init() {
        cl = new customer();
        gc = new GenericType<Tblcustomer>() {
        };
        if (email == null) {
            email = "";
        }
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            if (isUserLockedOut()) {
                return "login";
            }

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            HttpSession session = request.getSession();
            session.setAttribute("logged-group", "");

            Credential credential = new UsernamePasswordCredential(email, new Password(password));
            AuthenticationStatus status = securityContext.authenticate(request, response, withParams().credential(credential));

            if (status.equals(SEND_CONTINUE)) {
                context.responseComplete();
                return null;
            }

            rs = cl.getCustomersByEmail(Response.class, email);
            c = rs.readEntity(gc);
            
            if (c != null && roles != null && !roles.isEmpty()) {
                session.setAttribute("user-id", c.getCustomerId());
                session.setAttribute("loginMB", this);
                
                resetAttempts();

                System.out.println("customer id>>>" + c.getCustomerId());
                if (roles.contains("admin")) {
                    session.setAttribute("logged-group", "admin");
                    return "admin.jsf?faces-redirect=true";
                } else if (roles.contains("user") || roles.contains("employee")) {
                    if (roles.contains("user")) {
                        session.setAttribute("logged-group", "user");
                        session.setAttribute("user-email", email);
                        session.setAttribute("user-name", username);
                    } else if (roles.contains("employee")) {
                        session.setAttribute("logged-group", "employee");
                        session.setAttribute("user-email", email);
                        session.setAttribute("user-name", username);
                    }
                    System.out.println("session>>>>" + session.getAttribute("user-email"));
                    return "home.jsf?faces-redirect=true";
                }
            }
            
            handleFailedAttempt();
            
        } catch (Exception e) {
            handleFailedAttempt();
            e.printStackTrace();
        }
        return "login";
    }

    private static void addError(FacesContext context, String message) {
        context.addMessage(null, new FacesMessage(SEVERITY_ERROR, message, null));
    }

    public String logout() {
        resetAttempts();
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            request.logout();

            System.out.println("User logged out successfully.");

            facesContext.getExternalContext().redirect(request.getContextPath() + "/login.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session != null && session.getAttribute("logged-group") != null;
    }

    public void navigateToLoginPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../login.jsf");
    }

    public void navigateToRegisterPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../register.jsf");
    }

    public boolean isUserLockedOut() {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        Integer attempts = failedAttempts.get(email);
        if (attempts != null && attempts >= MAX_ATTEMPTS) {
            LocalDateTime lockoutTime = lockTime.get(email);
            if (lockoutTime != null) {
                long minutesSinceLockout = ChronoUnit.MINUTES.between(lockoutTime, LocalDateTime.now());
                if (minutesSinceLockout < LOCK_TIME_MINUTES) {
                    long remainingMinutes = LOCK_TIME_MINUTES - minutesSinceLockout;
                    message = "Account is temporarily locked. Please try again in " + remainingMinutes + " minute" + (remainingMinutes != 1 ? "s" : "") + ".";
                    return true;
                } else {
                    resetAttempts();
                }
            }
        }
        return false;
    }

    private void resetAttempts() {
        failedAttempts.remove(email);
        lockTime.remove(email);
    }

    private void handleFailedAttempt() {
        int attempts = failedAttempts.getOrDefault(email, 0) + 1;
        failedAttempts.put(email, attempts);

        if (attempts >= MAX_ATTEMPTS) {
            lockTime.put(email, LocalDateTime.now());
            message = "Too many failed attempts. Account is locked for " + LOCK_TIME_MINUTES + " minute.";
        } else {
            int remaining = MAX_ATTEMPTS - attempts;
            message = "Error: Username or Password is Incorrect! " + remaining + " attempt" + (remaining != 1 ? "s" : "") + " remaining.";
        }
    }
}
