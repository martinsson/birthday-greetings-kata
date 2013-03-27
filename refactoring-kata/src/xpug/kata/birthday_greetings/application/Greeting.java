package xpug.kata.birthday_greetings.application;

public class Greeting {

    private Employee employee;

    public Greeting(Employee employee) {
        this.employee = employee;
    }

    public String title() {
        return "Happy Birthday!";
    }

    public String phrasing() {
        return "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
    }

    public String getRecepientsEmail() {
        return employee.getEmail();
    }

    public String from() {
        return "sender@here.com";
    }

}
