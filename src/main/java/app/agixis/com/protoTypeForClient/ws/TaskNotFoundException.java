package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Could not find task id =  " + id);
    }
    public TaskNotFoundException(Task task) {
        super("Could not find task " + task);
    }
}
