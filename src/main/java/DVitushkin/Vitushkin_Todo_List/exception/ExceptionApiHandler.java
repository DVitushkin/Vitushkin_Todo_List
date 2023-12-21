package DVitushkin.Vitushkin_Todo_List.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DVitushkin.Vitushkin_Todo_List.response.ErrResponse;
import DVitushkin.Vitushkin_Todo_List.response.MultiErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.postgresql.util.PSQLException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;


@RestControllerAdvice
public class ExceptionApiHandler {

    private static List<String> parseValidateErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AbstractErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errsMsgs = parseValidateErrors(ex);
        if (errsMsgs.size() == 1) {
            var err = errsMsgs.get(0);
            return new ResponseEntity<>(new ErrResponse(err, ErrorMsg.getErrCodeByErrMsg(err), true), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MultiErrorResponse(errsMsgs,
                                                            errsMsgs
                                                                    .stream()
                                                                    .map(ErrorMsg::getErrCodeByErrMsg)
                                                                    .toList(),
                                                            true),
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AbstractErrorResponse> handleHttpMessageNotReadableExceptions(
            HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new ErrResponse(ErrorMsg.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getMsg(),
                                                    ErrorMsg.getErrCodeByErrMsg(ErrorMsg.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getMsg()),
                                            true),
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AbstractErrorResponse> handleEntityNotFoundExceptions(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ErrResponse(ErrorMsg.TASK_NOT_FOUND.getMsg(),
                                                    ErrorMsg.getErrCodeByErrMsg(ErrorMsg.TASK_NOT_FOUND.getMsg()),
                                            true),
                                    HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<AbstractErrorResponse> handleHandlerMethodValidationExceptions(HandlerMethodValidationException ex) {
        return new ResponseEntity<>(new ErrResponse(ErrorMsg.ID_MUST_BE_POSITIVE.getMsg(),
                ErrorMsg.getErrCodeByErrMsg(ErrorMsg.ID_MUST_BE_POSITIVE.getMsg()),
                true),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PSQLException.class)
    public Map<String, String> handlePSQLExceptions(PSQLException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("Error", "None");
        return errors;
    }
}
