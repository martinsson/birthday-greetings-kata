package xpug.kata.birthday_greetings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeRepository {

    public List<Employee> findEmployeesWhosBirthdayIs(OurDate ourDate) throws FileNotFoundException, IOException, ParseException;

}
