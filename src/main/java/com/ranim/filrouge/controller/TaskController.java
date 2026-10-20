package com.ranim.filrouge.controller;
import com.ranim.filrouge.entity.Task;
import com.ranim.filrouge.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //class y9bl l web requests w yraja3 donnée
@RequestMapping("/tasks") //les adresses te3i lkol yabdew b /tasks
public class TaskController {

    private final TaskRepository taskRepository;
    /* private =ken l class hetha ysta3melah final=ma3ach ttbadl , type ta3 l helper w esm l helper*/
    public TaskController (TaskRepository taskRepository)
    {
        this.taskRepository=taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() //list<task>= traja3 list of task objects tet7awl l format json
    {
        return taskRepository.findAll(); //Librarian, bring me all tasks
    }

    @PostMapping //wa9t li we7d yab3th request post sta3ml l method hethi
    public Task createTask(@RequestBody Task task) //5outh el json eli fl request w rodha objet Task
    {
        return taskRepository.save(task); //librarian, store this task in the table
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id); //a box that may contain a Task, or may be empty
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /*public Task getTaskById(@PathVariable Long id) //pathvar takes the data from the address on the parcel
    {
        return taskRepository.findById(id).orElse(null);
    }*/


     /* @GetMapping("/hello") //wa9t eli we7d y7eb ya9ra /tasks/hello sta3ml l method hello (type get)
    public String hello(){
          return "Hello my first controller!!!!";
      }*/

    @PutMapping("/{id}")
    //id = the number from the adress , newData = the Json from the parcel turned into task object
    public ResponseEntity<Task> updateTask(@PathVariable long id,@RequestBody Task newData)
    {
        //al9ali task li bl id hetha
        Optional<Task> existing=taskRepository.findById(id);

        if(existing.isPresent())
        {
            Task task=existing.get();
            task.setTitle(newData.getTitle());
            task.setDescription(newData.getDescription());
            task.setDone(newData.isDone()); //isdone mch getdone 5atrou boolean
            Task saved =taskRepository.save(task); //stori el task hethi
            /*
             Notice this is the same save we used for adding. 
             How does it know whether to add or to update? Because our task already has an id. 
             If the id exists, save updates that row. If there is no id, it creates a new one. 
             So the same method does both jobs.*/
            return ResponseEntity.ok(saved); //Send the updated task back in an envelope marked 200 OK.

        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}

