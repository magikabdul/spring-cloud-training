package pl.training.cloud.departments.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.cloud.departments.dto.ExceptionDto;
import pl.training.cloud.departments.service.DepartmentNotFoundException;

import java.util.Locale;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    @NonNull
    private MessageSource messageSource;

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity onOrganizationNotFound(DepartmentNotFoundException ex, Locale locale) {
        return createResponse(ex, NOT_FOUND, locale);
    }

    private ResponseEntity createResponse(Exception ex, HttpStatus status, Locale locale) {
        String exceptionName = ex.getClass().getSimpleName();
        String description;
        try {
            description = messageSource.getMessage(ex.getClass().getSimpleName(), null, locale);
        } catch (NoSuchMessageException exception) {
            description = exceptionName;

        }
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

}
