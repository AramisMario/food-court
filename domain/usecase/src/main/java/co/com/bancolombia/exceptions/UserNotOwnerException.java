package co.com.bancolombia.exceptions;

public class UserNotOwnerException extends RuntimeException{

    public UserNotOwnerException(){
        super("USER_NOT_OWNER");
    }

}
