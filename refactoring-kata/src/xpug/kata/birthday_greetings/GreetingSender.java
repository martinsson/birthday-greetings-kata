package xpug.kata.birthday_greetings;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GreetingSender {

    private Session session;

    public GreetingSender(Session session) {
        this.session = session;
    }

    void send(Greeting greeting) throws ApplicationException {
        Message msg = new MimeMessage(session);
        try {
            msg.setRecipient(RecipientType.TO, new InternetAddress(greeting.getRecepientsEmail()));
            msg.setSubject(greeting.title());
            msg.setText(greeting.phrasing());
            msg.setFrom(new InternetAddress(greeting.from()));
            
            // Send the message
            sendMessage(msg);
        } catch (AddressException e) {
            throw new ApplicationException(e);
        } catch (MessagingException e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * Protected for testing :(
     */
    protected void sendMessage(Message msg) throws MessagingException {
    	Transport.send(msg);
    }
    

}
