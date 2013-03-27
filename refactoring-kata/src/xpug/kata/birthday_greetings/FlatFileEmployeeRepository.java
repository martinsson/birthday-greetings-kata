package xpug.kata.birthday_greetings;

import java.io.BufferedReader;
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
    public List<Employee> getEmployeesWhosBirthdayIs(OurDate ourDate) throws ApplicationException {
        List<Employee> employees = new ArrayList<Employee>();
        try {
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
        } catch (IOException e) {
            throw new ApplicationException(e);
        } catch (ParseException e) {
            throw new ApplicationException(e);
        }
        return employees;
    }
}