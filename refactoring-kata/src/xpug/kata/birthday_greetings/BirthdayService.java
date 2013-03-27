package xpug.kata.birthday_greetings;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {

	public static final class FlatFileEmployeeRepository implements EmployeeRepository {
        private final String fileName;

        public FlatFileEmployeeRepository(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public List<Employee> getEmployeesWhosBirthdayIs(OurDate ourDate) throws IOException, ParseException {
            List<Employee> employees = new ArrayList<Employee>();
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str = "";
            str = in.readLine(); // skip header
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
                if (employee.isBirthday(ourDate)) {
                    employees.add(employee);
                }
            }
            return employees;
        }
    }

    public void sendGreetings(final String fileName, OurDate ourDate, String smtpHost, int smtpPort) throws IOException, ParseException, AddressException, MessagingException {
	    EmployeeRepository repository = new FlatFileEmployeeRepository(fileName) ;
	    List<Employee> employees = repository.getEmployeesWhosBirthdayIs(ourDate);
	    for (Employee employee : employees) {
	        // Create a mail session
            java.util.Properties props = new java.util.Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", "" + smtpPort);
            Session session = Session.getDefaultInstance(props, null);
            
            
            Greeting greeting = new Greeting(employee);
            String recipient = employee.getEmail();

            String subject = "Happy Birthday!";
            String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
            String from = "sender@here.com";
            // Construct the message
            Message msg = new MimeMessage(session);
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject(subject);
            msg.setText(body);
            msg.setFrom(new InternetAddress(from));
            
            // Send the message
            sendMessage(msg);
        }
	}

	// made protected for testing :-(
	protected void sendMessage(Message msg) throws MessagingException {
		Transport.send(msg);
	}

	public static void main(String[] args) {
		BirthdayService service = new BirthdayService();
		try {
			service.sendGreetings("employee_data.txt", new OurDate("2008/10/08"), "localhost", 25);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
