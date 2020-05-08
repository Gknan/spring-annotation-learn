package cn.hust.spring.component;

import org.springframework.stereotype.Component;

/**
 * 数学计算业务类
 */
public class MathCalculator {

    public int div(int i, int j) {

        System.out.println("MathCalculator div...");
        return i / j;
    }
}
