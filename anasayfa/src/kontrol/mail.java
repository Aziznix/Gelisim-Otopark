package kontrol;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class mail {
    public static void sendEmail(String to, String subject, String body) {
        String from = "iguotopark@gmail.com";
        String password = "vdik umar bosf afnp";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Kullanıcı adı ve şifre ile kimlik doğrulama
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Mesajı oluştur
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // Gönderen adresi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Alıcı adresi
            message.setSubject(subject); // Konu
            message.setText(body); // Mesaj içeriği

            // Mesajı gönder
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
