package ru.home.proj.tasklist.config.controller.advice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.home.proj.tasklist.exceptions.AccessDeniedExcept;
import ru.home.proj.tasklist.exceptions.ExceptionBody;
import ru.home.proj.tasklist.exceptions.ResourceMappingExcept;
import ru.home.proj.tasklist.exceptions.ResourceNotFoundExcept;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundExcept.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handlerResourceNotFound(ResourceNotFoundExcept e) {
        e.printStackTrace();

        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(ResourceMappingExcept.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handlerResourceMapping(ResourceMappingExcept e) {
        e.printStackTrace();

        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIllegalState(IllegalStateException e) {
        e.printStackTrace();

        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler({AccessDeniedExcept.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionBody handleAccessDenied(IllegalStateException e) {
        e.printStackTrace();

        return new ExceptionBody("Access denied");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleArgumentNotValid(MethodArgumentNotValidException e) {
        e.printStackTrace();

        ExceptionBody exceptionBody = new ExceptionBody("Validation failed");

        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();

        exceptionBody.setErrors(fieldErrorList.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));

        return exceptionBody;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleConstraintViolation(ConstraintViolationException e) {
        e.printStackTrace();

        ExceptionBody exceptionBody = new ExceptionBody("Validation failed");

        exceptionBody.setErrors(e.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        o -> o.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                )));

        return exceptionBody;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleAllOtherException(Exception e) {
        e.printStackTrace();

        return new ExceptionBody("Internal error");
    }
}
