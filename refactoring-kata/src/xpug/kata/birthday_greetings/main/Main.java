package xpug.kata.birthday_greetings.main;

import javax.mail.Session;

import xpug.kata.birthday_greetings.adapters.FlatFileEmployeeRepository;
import xpug.kata.birthday_greetings.adapters.JavaxMailGreetingSender;
import xpug.kata.birthday_greetings.application.BirthdayService;
import xpug.kata.birthday_greetings.application.OurDate;

public class Main {

    static Session getSession(String smtpHost, int smtpPort) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getDefaultInstance(props, null);
    }

    public static void main(String[] args) {
    	BirthdayService service = new BirthdayService(new JavaxMailGreetingSender(getSession("localhost", 25)), new FlatFileEmployeeRepository("employee_data.txt"));
    	try {
    		service.sendGreetings(new OurDate("2008/10/08"));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
