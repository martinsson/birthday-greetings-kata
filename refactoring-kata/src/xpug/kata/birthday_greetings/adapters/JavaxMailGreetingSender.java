package xpug.kata.birthday_greetings.adapters;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import xpug.kata.birthday_greetings.application.ApplicationException;
import xpug.kata.birthday_greetings.application.Greeting;
import xpug.kata.birthday_greetings.application.GreetingSender;


public class JavaxMailGreetingSender implements GreetingSender {

    private Session session;

    public JavaxMailGreetingSender(Session session) {
        this.session = session;
    }

    @Override
    public void send(Greeting greeting) throws ApplicationException {
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
