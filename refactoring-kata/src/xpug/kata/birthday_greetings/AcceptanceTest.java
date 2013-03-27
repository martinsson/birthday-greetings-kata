package xpug.kata.birthday_greetings;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AcceptanceTest {

	private List<Message> messagesSent;
	private BirthdayService service;
	
	@Before
	public void setUp() throws Exception {
		messagesSent = new ArrayList<Message>();

		GreetingSender greetingSender = new GreetingSender(Main.getSession("localhost", 25)) {
		    @Override
		    protected void sendMessage(Message msg) throws MessagingException {
		        messagesSent.add(msg);
		    }
		};

		service = new BirthdayService(greetingSender, new FlatFileEmployeeRepository("employee_data.txt"));
	}
	
	@Test
	public void baseScenario() throws Exception {
		
		service.sendGreetings("employee_data.txt", new OurDate("2008/10/08"));
		
		assertEquals("message not sent?", 1, messagesSent.size());
		Message message = messagesSent.get(0);
		assertEquals("Happy Birthday, dear John!", message.getContent());
		assertEquals("Happy Birthday!", message.getSubject());
		assertEquals(1, message.getAllRecipients().length);		
		assertEquals("john.doe@foobar.com", message.getAllRecipients()[0].toString());		
	}
	
	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {		
		service.sendGreetings("employee_data.txt", new OurDate("2008/01/01"));
		
		assertEquals("what? messages?", 0, messagesSent.size());
	}
}
