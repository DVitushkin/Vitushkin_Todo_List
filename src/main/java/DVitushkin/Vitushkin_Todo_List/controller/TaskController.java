package DVitushkin.Vitushkin_Todo_List.controller;

import DVitushkin.Vitushkin_Todo_List.dto.ChangeStatusTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.CreateTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.GetNewsDto;
import DVitushkin.Vitushkin_Todo_List.models.Task;
import DVitushkin.Vitushkin_Todo_List.response.BaseSuccessResponse;
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
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/v1/todo")
    public CustomSuccessResponse<Task> addTask(@Valid @RequestBody CreateTodoDto createTodoDto) {
        Task task = new Task();
        task.setText(createTodoDto.getText());
        return new CustomSuccessResponse<>(200, true, service.saveTask(task));
    }

    //    PATCH /v1/todo patch
    @PatchMapping("v1/todo")
    public BaseSuccessResponse changeStatus(@RequestBody ChangeStatusTodoDto changeStatusTodoDto) {
        service.updateStatusForAll(changeStatusTodoDto.isStatus());
        return new BaseSuccessResponse(200, true);
    }

    //    DELETE /v1/todo deleteAllReady
    @DeleteMapping("v1/todo")
    public BaseSuccessResponse deleteAllReady() {
        service.deleteAllReadyTasks();
        return new BaseSuccessResponse(200, true);
    }

    //    GET /v1/todo getPaginated
    @GetMapping("v1/todo")
    public CustomSuccessResponse<GetNewsDto> getPaginated(@RequestParam("page") int page, @RequestParam("perPage") int perPage) {
        GetNewsDto getNewsDto = service.getPage(page, perPage);
        return new CustomSuccessResponse<>(200, true, getNewsDto);
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
