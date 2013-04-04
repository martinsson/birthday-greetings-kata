package xpug.kata.birthday_greetings;

public class Main {

    public static void main(String[] args) {
    	BirthdayService service = new BirthdayService(new FlatFileEmployeeRepository("employee_data.txt"));
    	try {
    		service.sendGreetings(new OurDate("2008/10/08"), "localhost", 25);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
