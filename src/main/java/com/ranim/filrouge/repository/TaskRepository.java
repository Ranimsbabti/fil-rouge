package com.ranim.filrouge.repository;
import com.ranim.filrouge.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task,Long>{
}
