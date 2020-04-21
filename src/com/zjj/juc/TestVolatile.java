package com.zjj.juc;

/*
 * volatile 关键字：当多个线程操作共享数据时，可以保证内存中的数据时可见的
 *                  相较于synchronized 是一种较为轻量级的同步策略。
 *
 * 注意：
 * 1. volatile 不具备“互斥性”
 * 2. volatile 不能保证变量的“原子性”
 *
 */
public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while (true) {
//            synchronized (td)
            if (td.isFlag()) {
                System.out.println("---------");
                break;
            }

        }
    }

}

class ThreadDemo implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        flag = true;
        try {
            Thread.sleep(200);
            System.out.println("flag=" + isFlag());
        } catch (InterruptedException e) {

        }

    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
