
package xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class GreetingsTest {

    @Test
    public void inserts_employees_name_in_greeting() throws ParseException {
        Employee employee = new Employee("Fred", null, "1965/11/11", null);
        Greeting greeting = new Greeting(employee );
        assertEquals("Happy Birthday, dear Fred!", greeting.phrasing());
    }
    
    @Test public void 
    greeting_title_is_Happy_Birthday() throws Exception {
         Greeting greeting = new Greeting(null);
         assertEquals("Happy Birthday!", greeting.subject());
    }

}
