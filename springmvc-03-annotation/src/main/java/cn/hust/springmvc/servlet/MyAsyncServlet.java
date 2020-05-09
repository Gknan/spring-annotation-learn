package cn.hust.springmvc.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义异步 Servlet
 */
@WebServlet(value = "/async", asyncSupported = true) // 开启异步支持
public class MyAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 开启异步支持
        req.startAsync();

        // 获取异步上下文
        AsyncContext asyncContext = req.getAsyncContext();

        System.out.println("doGet start Current thread: " + Thread.currentThread());

        /**
         * Causes the container to dispatch a thread, possibly from a managed
         * thread pool, to run the specified <tt>Runnable</tt>. The container may
         * propagate appropriate contextual information to the <tt>Runnable</tt>.
         */
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("processService start finish Current thread: " + Thread.currentThread());
                    processService();
                    // 从 context 中获取响应，返回结果
                    ServletResponse response = asyncContext.getResponse();
                    response.getWriter().write("Async succcess");
                    System.out.println("processService finish Current thread: " + Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        System.out.println("doGet done Current thread: " + Thread.currentThread());
    }

    public void processService() throws InterruptedException {
        System.out.println("processService ing Current thread: " + Thread.currentThread());
        Thread.sleep(3000);
    }
}
