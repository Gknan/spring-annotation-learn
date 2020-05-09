package cn.hust.springmvc.controller;


import cn.hust.springmvc.servlet.DeferredResultQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

@Controller
public class AsynController {

    @Autowired
    DeferredResultQueue deferredResultQueue;

    @GetMapping("/testasyn")
    public Callable<String> testAsync() {
        System.out.println("controller start..." + Thread.currentThread());

        System.out.println("processService start Current thread: " + Thread.currentThread());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                processService();
                System.out.println("processService end Current thread: " + Thread.currentThread());
                return "success";
            }
        };

        System.out.println("controller end..." + Thread.currentThread());
        return callable;
    }

    @ResponseBody
    @GetMapping("/testasyn2")
    public DeferredResult<String> testAsync2() {
        // 创建 DeferdResult 并返回，可以设置超时时间，如果监听到 DefredResult.setData 则返回
        DeferredResult<String> deferredResult = new DeferredResult<String>(3000L, new String("timeout"));

        deferredResultQueue.add(deferredResult);
        return deferredResult;
    }

    @ResponseBody
    @GetMapping("/create")
    public String create() {
        DeferredResult<String> deferredResult = deferredResultQueue.get();
        String order = UUID.randomUUID().toString();
        deferredResult.setResult(order);
        return order;
    }

    public void processService() throws InterruptedException {
        System.out.println("processService ing Current thread: " + Thread.currentThread());
        Thread.sleep(3000);
    }
}
