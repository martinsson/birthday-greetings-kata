package xpug.kata.birthday_greetings.application;

public interface GreetingSender {

    void send(Greeting greeting) throws ApplicationException;

}