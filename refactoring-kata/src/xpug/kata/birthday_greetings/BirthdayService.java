package xpug.kata.birthday_greetings;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class BirthdayService {

    private EmployeeRepository employeeRepository;

    public BirthdayService(EmployeeRepository repository) {
        employeeRepository = repository;
    }
    
	public void sendGreetings(OurDate today, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {
        List<Employee> employees = employeeRepository.findEmployeesWhosBirthdayIs(today);
		for (Employee employee : employees) {
		    Greeting greeting = new Greeting(employee);
            Session session = createMailSession(smtpHost, smtpPort);
            
            // Construct the message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(greeting.getSender()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(greeting.getRecepient()));
            msg.setSubject(greeting.subject());
            msg.setText(greeting.phrasing());
            
            // Send the message
            sendMessage(msg);
        }
	}

    private Session createMailSession(String smtpHost, int smtpPort) {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getDefaultInstance(props, null);
        return session;
    }

    // made protected for testing :-(
	protected void sendMessage(Message msg) throws MessagingException {
		Transport.send(msg);
	}
}
