package mail;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
import javax.enterprise.context.Dependent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Dependent
public class mail  implements Serializable{

    public void sendEmail(String uemail,int ordid,Date orderDate) {
        try {
            String host = "smtp.gmail.com";
            final String from = "panchalaayush72@gmail.com"; // your Gmail account
            final String password1 = "wnyx kjtm djwc yrce"; // your Gmail app-specific password
            String port = "465";
            String to = uemail;

            Properties prop = new Properties();
            prop.put("mail.smtp.user", from);
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", port);
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.debug", "true");
            prop.put("mail.smtp.socketFactory.port", port);
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.socketFactory.fallback", "false");

            Session session1 = Session.getInstance(prop, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password1);
                }
            });

            session1.setDebug(true);

            MimeMessage message = new MimeMessage(session1);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Laundry Management");

            String htmlcode = "<p style='font-size:16px'> Thank You.</p><h3><h4> Your Laundry Order Id is:"+ordid+""
                    + "On"+orderDate+"</h4>";
            message.setContent(htmlcode, "text/html");
            
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException ex1) {
            ex1.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
//        mail emailSender = new mail();
//        emailSender.sendEmail("recipient@example.com");
    }
}
    
