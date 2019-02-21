package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Task;
import app.agixis.com.protoTypeForClient.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("/savetasks")
    public Task saveTasks(@RequestBody Task task) {
        return taskRepository.save(task);
    }
}
