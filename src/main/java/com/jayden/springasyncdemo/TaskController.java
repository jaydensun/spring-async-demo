package com.jayden.springasyncdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/doSyncTask")
    public String doSyncTask() {
        long start = System.currentTimeMillis();
        taskService.doTask1();
        taskService.doTask2();
        long consume = System.currentTimeMillis() - start;
        System.out.println(consume);
        return "consume " + consume + " ms";
    }

    @RequestMapping("/doAsyncTask")
    public String doAsyncTask() {
        long start = System.currentTimeMillis();
        taskService.doAsyncTask1();
        taskService.doAsyncTask2();
        long consume = System.currentTimeMillis() - start;
        System.out.println(consume);
        return "consume " + consume + " ms";
    }

    @RequestMapping("/doAsyncReturnTask")
    public String doAsyncReturnTask() {
        long start = System.currentTimeMillis();
        Future<String> task1Result = taskService.doAsyncReturnTask1();
        Future<String> task2Result = taskService.doAsyncReturnTask2();
        String result;
        try {
            result = "task1: " + task1Result.get() + "， task2: " + task2Result.get();
        } catch (InterruptedException | ExecutionException e) {
            return "error occur!";
        }
        long consume = System.currentTimeMillis() - start;
        System.out.println(consume);
        return "consume " + consume + " ms, " + result;
    }

}
