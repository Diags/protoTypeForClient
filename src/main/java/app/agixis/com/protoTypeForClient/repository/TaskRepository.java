package app.agixis.com.protoTypeForClient.repository;

import app.agixis.com.protoTypeForClient.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
