package cn.hust.spring.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 日志切面类
 */
@Aspect
public class LogAspect {

    // 抽取被增强的动态的部分
    @Pointcut("execution(public int cn.hust.spring.component.MathCalculator.*(..))")
    public void pointCut() {}

    @Before("execution(public int cn.hust.spring.component.MathCalculator.*(..))")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + " 开始运行");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + " 运行结束");
    }

    @AfterReturning(value = "pointCut()", returning = "ret")
    public void logReturn(JoinPoint joinPoint, int ret) {
        System.out.println(joinPoint.getSignature().getName() + " 返回值是 "+ ret);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + " 抛出异常 " + exception);
    }

}
