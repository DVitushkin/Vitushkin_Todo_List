package DVitushkin.Vitushkin_Todo_List.service;

import java.util.List;

import DVitushkin.Vitushkin_Todo_List.response.CustomSuccessResponse;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.CreateTodoDto;
import DVitushkin.Vitushkin_Todo_List.dto.taskDto.GetNewsDto;
import DVitushkin.Vitushkin_Todo_List.models.Task;
import DVitushkin.Vitushkin_Todo_List.repository.TaskRepository;

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

        return new CustomSuccessResponse<>(repository.save(task), 0, true);
    }

    public List<Task> getTasks() {
        return repository.findAll();
    }

    public Task getTaskById(long id) {
        return repository.findById(id).orElseThrow();
    }

    public Task updateTask(Task task) {
        long id = task.getId();
        Task updatedTask = repository.getReferenceById(id);

        updatedTask.setText(task.getText());
        updatedTask.setStatus(task.isStatus());
        updatedTask.setCreatedAt(task.getCreatedAt());
        updatedTask.setUpdatedAt(task.getUpdatedAt());

        return repository.save(updatedTask);
    }

    public void updateStatusForAll(boolean status) {
        repository.changeStatusForAll(status);
    }

    public void deleteAllReadyTasks() {
        repository.deleteAllByStatus(true);
    }

    public CustomSuccessResponse<GetNewsDto> getPage(int page, int perPage) {
        Page<Task> contentPage = repository.findAll(PageRequest.of(page, perPage));

        List<Task> pageTasks = contentPage.getContent();
        var notReady = (int) pageTasks.stream()
                .filter(Task::isStatus)
                .count();

        GetNewsDto getNewsDto = new GetNewsDto();
        getNewsDto.setNumberOfElements((int) contentPage.getTotalElements());
        getNewsDto.setNotReady(notReady);
        getNewsDto.setReady(getNewsDto.getNumberOfElements() - notReady);
        getNewsDto.setContent(pageTasks);

        return new CustomSuccessResponse<>(getNewsDto, 200, true);
    }


    public void setStatusById(int id, boolean status) {
        repository.setStatusById(id, status);
    }

    public void setTextById(int id, String text) {
        repository.setTextById(id, text);
    }

    public void deleteTaskById(int id) {
        repository.deleteById((long) id);
    }
}
