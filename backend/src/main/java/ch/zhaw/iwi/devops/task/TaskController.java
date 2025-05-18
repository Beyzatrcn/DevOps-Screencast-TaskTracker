package ch.zhaw.iwi.devops.task;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }
}