package xpug.kata.birthday_greetings;


public class ApplicationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ApplicationException(Exception e) {
        super(e);
    }

}
