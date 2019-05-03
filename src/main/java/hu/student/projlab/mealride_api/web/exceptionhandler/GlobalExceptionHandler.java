package hu.student.projlab.mealride_api.web.exceptionhandler;

import com.google.gson.Gson;
import hu.student.projlab.mealride_api.domain.Order;
import hu.student.projlab.mealride_api.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // TODO maybe the exceptions should be refactored as RuntimeExceptions? find out!

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> handleElementNotFound() {
        return new ResponseEntity<>(wrapMessageToJSON("Elements not found."), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIsNotAuthenticatedException.class)
    protected ResponseEntity<?> handleUserNotAuthenticated(UserIsNotAuthenticatedException e) {
        return new ResponseEntity<>(wrapMessageToJSON(e.getMessage()), null, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidDataException.class)
    protected ResponseEntity<?> handleInvalidData(InvalidDataException e) {
        return new ResponseEntity<>(wrapMessageToJSON(e.getMessage()), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<?> handleAccessDenied(AccessDeniedException e) {
        return new ResponseEntity<>(wrapMessageToJSON(e.getMessage()), null, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AlreadyAddedToRestaurantException.class)
    protected ResponseEntity<?> handleAlreadyAddedToRestaurant(AlreadyAddedToRestaurantException e) {
        return new ResponseEntity<>(wrapMessageToJSON(e.getMessage()), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    protected ResponseEntity<?> handleRestaurantNotFound(RestaurantNotFoundException e) {
        return new ResponseEntity<>(wrapMessageToJSON(e.getMessage()), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderException.class)
    protected ResponseEntity<?> handleOrderFailure(OrderException e) {
        return new ResponseEntity<>(wrapMessageToJSON(e.getMessage()), null, HttpStatus.BAD_REQUEST);
    }

    private String wrapMessageToJSON(String message) {
        return new Gson().toJson(new Message(message));
    }

}
