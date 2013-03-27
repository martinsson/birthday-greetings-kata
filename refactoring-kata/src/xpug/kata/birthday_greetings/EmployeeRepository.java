package xpug.kata.birthday_greetings;

import java.util.List;

public interface EmployeeRepository {

    public List<Employee> getEmployeesWhosBirthdayIs(OurDate ourDate) throws ApplicationException;

}
