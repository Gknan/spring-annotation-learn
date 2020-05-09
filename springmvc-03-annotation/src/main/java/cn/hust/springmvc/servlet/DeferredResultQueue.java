package cn.hust.springmvc.servlet;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class DeferredResultQueue {

    private Queue<DeferredResult> innerQueue = new ConcurrentLinkedDeque<>();

    public void add(DeferredResult result) {
        innerQueue.add(result);
    }

    public DeferredResult<String> get() {
        return innerQueue.poll();
    }
}
