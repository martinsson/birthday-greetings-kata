package xpug.kata.birthday_greetings;

public class FlatFileEmployeeRepositoryTest extends EmployeeRepositoryTest {
    @Override
    protected FlatFileEmployeeRepository newEmployeeRepository() {
        return new FlatFileEmployeeRepository("employee_data.txt");
    }
}
