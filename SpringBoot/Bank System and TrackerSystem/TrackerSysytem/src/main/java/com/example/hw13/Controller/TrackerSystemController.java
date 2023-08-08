package com.example.hw13.Controller;

import com.example.hw13.ApiResponse.ApiResponse;
import com.example.hw13.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/trackersystem/")
public class TrackerSystemController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("tasks")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @PostMapping("create")
    public String addTask(@RequestBody Task task) {
        tasks.add(task);
        return "is added to the system";
    }

    @PutMapping("update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return new ApiResponse("is updated to the system", "done");
    }

    @DeleteMapping("delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("is deleted from the system", "done");
    }

    @PutMapping("change/status/{title}")
    public ApiResponse changeStatus(@PathVariable String title) {
        for (Task newTasks : tasks) {
            if (newTasks.getTitle().equals(title)) {
                if (newTasks.getStatus().equals("done")) {
                    newTasks.setStatus("not done");
                } else if (newTasks.getStatus().equals("not done")) {
                    newTasks.setStatus("done");
                }
            }
        }
        return new ApiResponse("is changed", "done");
    }

    @PutMapping("search/{title}")
    public Task searchTask(@PathVariable String title) {
        for (Task newTasks1 : tasks) {
            if (newTasks1.getTitle().equals(title)) {
                System.out.println("is found");
                return newTasks1;
            } else {
                System.out.println("not fount task ");
            }
        }
        return new Task(0, "000", "not found", "not done");
    }

}
