package xpug.kata.birthday_greetings;

import javax.mail.Session;

public class Main {

    static Session getSession(String smtpHost, int smtpPort) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getDefaultInstance(props, null);
    }

    public static void main(String[] args) {
    	BirthdayService service = new BirthdayService(new GreetingSender(getSession("localhost", 25)), new FlatFileEmployeeRepository("employee_data.txt"));
    	try {
    		service.sendGreetings("employee_data.txt", new OurDate("2008/10/08"));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
