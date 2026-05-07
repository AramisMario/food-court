package co.com.bancolombia.exceptions;

public class UserNotOwnRestaurantException extends RuntimeException {

    public UserNotOwnRestaurantException() {
        super("USER_NOT_OWN_RESTAURANT");
    }

}