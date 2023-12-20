package DVitushkin.Vitushkin_Todo_List.controller;

import DVitushkin.Vitushkin_Todo_List.dto.taskDto.*;
import DVitushkin.Vitushkin_Todo_List.response.BaseSuccessResponse;
import DVitushkin.Vitushkin_Todo_List.response.CustomSuccessResponse;
import DVitushkin.Vitushkin_Todo_List.models.Task;
import DVitushkin.Vitushkin_Todo_List.service.TaskService;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@Value
public class TaskController {
    @Autowired
    private final TaskService service;

    @PostMapping("/v1/todo")
    public ResponseEntity<CustomSuccessResponse<Task>> addTask(@Validated @RequestBody CreateTodoDto createTodoDto) {
        return new ResponseEntity<>(service.saveTask(createTodoDto), HttpStatus.OK);
    }

    @PatchMapping("v1/todo")
    public ResponseEntity<BaseSuccessResponse> changeStatus(@Validated @RequestBody ChangeStatusTodoDto changeStatusTodoDto) {
        return new ResponseEntity<>(service.updateStatusForAll(changeStatusTodoDto.getStatus()), HttpStatus.OK);
    }

    @DeleteMapping("v1/todo")
    public ResponseEntity<BaseSuccessResponse> deleteAllReady() {
        return new ResponseEntity<>(service.deleteAllReadyTasks(), HttpStatus.OK);
    }

    @GetMapping("v1/todo")

    public ResponseEntity<CustomSuccessResponse<GetNewsDto>> getPaginated(@Validated GetPaginatedTaskDto getPaginatedTaskDto) {
        return new ResponseEntity<>(service.getPage(getPaginatedTaskDto.getPage(),
                                                    getPaginatedTaskDto.getPerPage(),
                                                    getPaginatedTaskDto.isStatus()
                                                    ),
                                    HttpStatus.OK);
    }

    @PatchMapping("v1/todo/status/{id}")
    public ResponseEntity<BaseSuccessResponse> patchStatusById(@PathVariable("id") int id, @RequestBody ChangeStatusTodoDto changeStatusTodoDto) {
        return new ResponseEntity<>(service.setStatusById(id, changeStatusTodoDto.getStatus()), HttpStatus.OK);
    }

    @PatchMapping("v1/todo/text/{id}")
    public ResponseEntity<BaseSuccessResponse> patchTextById(@PathVariable("id") int id, @RequestBody ChangeTextTodoDto changeTextTodoDto) {
        return new ResponseEntity<>(service.setTextById(id, changeTextTodoDto.getText()), HttpStatus.OK);
    }

    @DeleteMapping("v1/todo/{id}")
    public ResponseEntity<BaseSuccessResponse> deleteTaskById(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.deleteTaskById(id), HttpStatus.OK);
    }
}
