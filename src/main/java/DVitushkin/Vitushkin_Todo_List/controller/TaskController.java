package DVitushkin.Vitushkin_Todo_List.controller;

import DVitushkin.Vitushkin_Todo_List.dto.taskDto.ChangeStatusTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.ChangeTextTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.CreateTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.GetNewsDto;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.GetPaginatedTaskDto;
import DVitushkin.Vitushkin_Todo_List.models.Task;
import DVitushkin.Vitushkin_Todo_List.response.BaseSuccessResponse;
import DVitushkin.Vitushkin_Todo_List.response.CustomSuccessResponse;
import DVitushkin.Vitushkin_Todo_List.service.TaskService;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class TaskController {
    @Autowired
    TaskService service;

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
    public ResponseEntity<BaseSuccessResponse> patchStatusById(@PathVariable("id") Long id, @Validated @RequestBody ChangeStatusTodoDto changeStatusTodoDto) {
        return new ResponseEntity<>(service.setStatusById(id, changeStatusTodoDto.getStatus()), HttpStatus.OK);
    }

    @PatchMapping("v1/todo/text/{id}")
    public ResponseEntity<BaseSuccessResponse> patchTextById(@PathVariable("id") Long id, @Validated @RequestBody ChangeTextTodoDto changeTextTodoDto) {
        return new ResponseEntity<>(service.setTextById(id, changeTextTodoDto), HttpStatus.OK);
    }

    @DeleteMapping("v1/todo/{id}")
    public ResponseEntity<BaseSuccessResponse> deleteTaskById(@Validated @PathVariable("id") @Positive(message = "ID must be grater than zero") Long id) {
        return new ResponseEntity<>(service.deleteTaskById(id), HttpStatus.OK);
    }
}
