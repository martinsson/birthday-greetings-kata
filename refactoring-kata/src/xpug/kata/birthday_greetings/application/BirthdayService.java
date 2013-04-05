package xpug.kata.birthday_greetings.application;

import java.util.List;


public class BirthdayService {

	private GreetingSender greetingSender;
    private EmployeeRepository repository;

	public BirthdayService(GreetingSender greetingSender, EmployeeRepository repository) {
        this.greetingSender = greetingSender;
        this.repository = repository;
	}
	
    public void sendGreetings(OurDate ourDate) throws ApplicationException {
	    List<Employee> employees = repository.getEmployeesWhosBirthdayIs(ourDate);
	    for (Employee employee : employees) {
	        Greeting greeting = new Greeting(employee);
            greetingSender.send(greeting);
        }
	}
}
