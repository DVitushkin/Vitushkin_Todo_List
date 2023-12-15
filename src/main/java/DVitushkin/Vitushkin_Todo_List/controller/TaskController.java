package DVitushkin.Vitushkin_Todo_List.controller;

import DVitushkin.Vitushkin_Todo_List.dto.CreateTodoDto;
import DVitushkin.Vitushkin_Todo_List.models.Task;
import DVitushkin.Vitushkin_Todo_List.response.CustomSuccessResponse;
import DVitushkin.Vitushkin_Todo_List.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TaskController {
    @Autowired
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/v1/todo")
    public CustomSuccessResponse<Task> addTask(@Valid @RequestBody CreateTodoDto createTodoDto) {
        Task task = new Task();
        task.setText(createTodoDto.getText());
        return new CustomSuccessResponse<>(200, true, service.saveTask(task));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            });
            return errors;
    }
}
