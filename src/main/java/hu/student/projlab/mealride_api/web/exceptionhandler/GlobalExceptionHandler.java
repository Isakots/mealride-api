package hu.student.projlab.mealride_api.web.exceptionhandler;

import hu.student.projlab.mealride_api.exception.UserIsNotAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> handleElementNotFound() {
        return new ResponseEntity<>("Elements not found", null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIsNotAuthenticatedException.class)
    protected ResponseEntity<?> handleUserNotAuthenticated(UserIsNotAuthenticatedException e) {
        return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
    }

}
