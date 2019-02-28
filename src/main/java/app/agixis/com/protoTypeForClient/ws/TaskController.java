package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Task;
import app.agixis.com.protoTypeForClient.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.*;

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

    @GetMapping(value = "/tasks/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Task>  getTaskById(@PathVariable("id")Long id){
        return new ResponseEntity<>( taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletetask/{id}", produces = APPLICATION_JSON_VALUE)
    public void  deleteTaskById(@PathVariable("id")Long id){
        if(id == null) throw new TaskNotFoundException(id);
       taskRepository.deleteById(id);
    }

    @PostMapping("/updatetask")
    public ResponseEntity<Task>  updatetask(@RequestBody Task task){
        if(task == null) throw new TaskNotFoundException(task);
        Task taskFind = taskRepository.findById(task.getId()).orElseThrow(()->  new TaskNotFoundException(task.getId()));
        taskFind.setMessage(task.getMessage());
        taskRepository.save(taskFind);
        return new ResponseEntity<>( taskRepository.findById(taskFind.getId()).orElseThrow(()-> new TaskNotFoundException(taskFind.getId())), HttpStatus.OK);
    }
}
