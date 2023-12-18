package DVitushkin.Vitushkin_Todo_List.service;

import DVitushkin.Vitushkin_Todo_List.dto.GetNewsDto;
import DVitushkin.Vitushkin_Todo_List.models.Task;
import DVitushkin.Vitushkin_Todo_List.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task) {
        return repository.save(task);
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
}
