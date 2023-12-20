package DVitushkin.Vitushkin_Todo_List.exception;

import java.util.HashMap;
import java.util.Map;

import DVitushkin.Vitushkin_Todo_List.response.ErrResponse;
import org.postgresql.util.PSQLException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionApiHandler {

    private static String parseValidateErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList()
                .get(0);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        String errsMsgs = parseValidateErrors(ex);
        return new ResponseEntity<>(new ErrResponse(errsMsgs, ErrorMsg.getErrCodeByErrMsg(errsMsgs), true), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PSQLException.class)
    public Map<String, String> handlePSQLExceptions(
            PSQLException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("Error", "None");
        return errors;
    }
}
