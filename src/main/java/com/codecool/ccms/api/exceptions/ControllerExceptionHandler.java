package com.codecool.ccms.api.exceptions;

import com.codecool.ccms.api.utils.MessageDTO;
import com.codecool.ccms.api.utils.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private MessageSource msgSource;

    private final String error404JSON = "{\n" +
            "  \"key\": \"NotFoundError\",\n" +
            "  \"msg\": \"Resource not found\"\n" +
            "}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity<>(processFieldError(fieldErrors), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundHandler(NotFoundException ex) {
        return new ResponseEntity<>(error404JSON, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity emptyFieldOnUpdate() {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private List<MessageDTO> processFieldError(List<FieldError> errors) {
        List<MessageDTO> messageDTOList = new ArrayList<>();
        if (errors != null) {
            for (FieldError error:errors
                 ) {
                Locale currentLocale = LocaleContextHolder.getLocale();
                String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
               messageDTOList.add(new MessageDTO(MessageType.ERROR, msg));
            }
        }
        return messageDTOList;
    }
}