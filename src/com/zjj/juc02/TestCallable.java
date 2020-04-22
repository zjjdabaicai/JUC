package com.zjj.juc02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
一、创建执行线程的方式三：实现 Callabel 接口。
        相较于实现 Runnable 接口的方式，方式可以有返回值，并且可以抛出异常

    执行Callabel 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask 是 Future 接口是实现类

 */
public class TestCallable {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();

        //1. 执行Callabel 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> result = new FutureTask<Integer>(td);

        new Thread(result).start();

        // 2. 接收线程运算后的结果
        try {
            Integer sum = result.get(); // FutureTask 也可用于 闭锁
            System.out.println(sum);
            System.out.println("---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class ThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 1000000; i++) {
            sum += i;
        }

        return sum;
    }
}
