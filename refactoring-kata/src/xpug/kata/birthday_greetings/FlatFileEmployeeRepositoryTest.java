package xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class FlatFileEmployeeRepositoryTest {
    EmployeeRepository repository = new FlatFileEmployeeRepository("employee_data.txt");

    @Test public void 
    findWhosBirthdayIs_returns_empty_list_if_none_have_birthday_today() throws Exception {
         List<Employee> employees = repository.findEmployeesWhosBirthdayIs(new OurDate("1965/01/01"));
        assertEquals(0, employees.size());
    }
    
    @Test public void 
    findWhosBirthday_returns_employees_whos_birthday_is_today() throws Exception {
         List<Employee> employees = repository.findEmployeesWhosBirthdayIs(new OurDate("1975/03/11"));
         assertEquals(1, employees.size());
         Employee maryAnn = new Employee("Mary", "Ann", "1975/03/11", "mary.ann@foobar.com");
         assertTrue(employees.contains(maryAnn));
    }
}
