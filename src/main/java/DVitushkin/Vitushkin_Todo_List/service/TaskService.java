package DVitushkin.Vitushkin_Todo_List.service;

import java.util.List;

import DVitushkin.Vitushkin_Todo_List.dto.taskDto.ChangeStatusTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.ChangeTextTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.CreateTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.GetNewsDto;
import DVitushkin.Vitushkin_Todo_List.models.Task;
import DVitushkin.Vitushkin_Todo_List.repository.TaskRepository;
import DVitushkin.Vitushkin_Todo_List.response.BaseSuccessResponse;
import DVitushkin.Vitushkin_Todo_List.response.CustomSuccessResponse;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public CustomSuccessResponse<Task> saveTask(CreateTodoDto createTodoDto) {
        Task task = new Task();
        task.setText(createTodoDto.getText());

        return CustomSuccessResponse.data(repository.save(task));
    }

    public BaseSuccessResponse updateStatusForAll(Boolean status) {
        repository.changeStatusForAll(status);
        return BaseSuccessResponse.ok();
    }

    public BaseSuccessResponse deleteAllReadyTasks() {
        repository.deleteAllByStatus(true);
        return BaseSuccessResponse.ok();
    }

    public CustomSuccessResponse<GetNewsDto> getPage(Integer page, Integer perPage, Boolean status) {
        Page<Task> contentPage = repository.findAllByStatus(status, PageRequest.of(page - 1, perPage));

        List<Task> pageTasks = contentPage.getContent();
        var notReady = (int) pageTasks.stream()
                .filter(Task::isStatus)
                .count();

        GetNewsDto getNewsDto = new GetNewsDto();
        getNewsDto.setNumberOfElements((int) contentPage.getTotalElements());
        getNewsDto.setNotReady(notReady);
        getNewsDto.setReady(getNewsDto.getNumberOfElements() - notReady);

        getNewsDto.setContent(pageTasks);

        return CustomSuccessResponse.data(getNewsDto);
    }

    public BaseSuccessResponse setStatusById(Long id, ChangeStatusTodoDto changeStatusTodoDto) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.setStatusById(id, changeStatusTodoDto.getStatus());
        return BaseSuccessResponse.ok();
    }

    public BaseSuccessResponse setTextById(Long id, ChangeTextTodoDto changeTextTodoDto) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.setTextById(id, changeTextTodoDto.getText());
        return BaseSuccessResponse.ok();
    }

    public BaseSuccessResponse deleteTaskById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
        return BaseSuccessResponse.ok();
    }
}
