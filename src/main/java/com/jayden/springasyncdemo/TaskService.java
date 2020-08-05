package com.jayden.springasyncdemo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@Service
public class TaskService {

    @Async
    public void doAsyncTask2() {
        doTask2();
    }

    @Async
    public void doAsyncTask1() {
        doTask1();
    }

    public void doTask1() {
        sleep(2000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep end at " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
    }

    public void doTask2() {
        sleep(3000);
    }

    @Async
    public Future<String> doAsyncReturnTask1() {
        doTask1();
        return new AsyncResult<>("task1 finish!");
    }

    @Async
    public Future<String> doAsyncReturnTask2() {
        doTask2();
        return new AsyncResult<>("task2 finish!");
    }
}
