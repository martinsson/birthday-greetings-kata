package xpug.kata.birthday_greetings;

public class Greeting {

    private Employee employee;

    public Greeting(Employee employee) {
        this.employee = employee;
    }

    public String phrasing() {
        return "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
    }

    public String subject() {
        return "Happy Birthday!";
    }

    public String getRecepient() {
        return employee.getEmail();
    }

    public String getSender() {
        return "sender@here.com";
    }

}
