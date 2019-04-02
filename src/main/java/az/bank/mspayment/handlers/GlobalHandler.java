package az.bank.mspayment.handlers;

import az.bank.mspayment.exceptions.ErrorInfo;
import az.bank.mspayment.exceptions.WrongPaymentChoiceException;
import az.bank.mspayment.exceptions.WrongPaymentChoiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongPaymentChoiceException.class)
    public ErrorInfo noSuchAccountExceptionHandler
            (HttpServletRequest request, WrongPaymentChoiceException exception) {
        String message = exception.getLocalizedMessage();
        ErrorInfo errorInfo = ErrorInfo
                .builder()
                .message(message)
                .build();
        return errorInfo;
    }


}
