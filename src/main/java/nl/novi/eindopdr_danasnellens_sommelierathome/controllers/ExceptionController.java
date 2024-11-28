package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin //TODO of niet nodig bij controlleradvice?
//TODO aanpassen naar eigen project
//Zie HW-klas 13.2 00:06:00 voor uitleg
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {
        return new ResponseEntity<>("Dit id staat niet in de database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> exception (MethodArgumentNotValidException exception){
        return new ResponseEntity<>(exception.getBindingResult().getFieldErrors().stream().
                map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage()).
                collect(Collectors.toList()), HttpStatus.BAD_REQUEST );
    }
}
