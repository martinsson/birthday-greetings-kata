package xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public final class FlatFileEmployeeRepository implements EmployeeRepository {
    private final String fileName;

    public FlatFileEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> findEmployeesWhosBirthdayIs(OurDate ourDate) throws FileNotFoundException, IOException, ParseException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str = "";
        str = in.readLine(); // skip header
        List<Employee> employees = new ArrayList<Employee>();
        
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