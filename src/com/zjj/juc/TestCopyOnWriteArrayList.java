package com.zjj.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*

CopyOnWriteArrayList/CopyOnWriteArraySet : "写入并复制"
注意：添加操作多时，效率低，因为每次添加时都会进行一次复制，开销非常大，并发迭代操作多时可以选择

    比同步锁效率高

 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        HelloThread ht = new HelloThread();

        for (int i = 0; i < 10; i++) {
            new Thread(ht).start();
        }
    }
}

class HelloThread implements Runnable {
    // java.util.ConcurrentModificationException
//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();

        while (it.hasNext()) {

            System.out.println(it.next());
            list.add("AA");
        }
    }
}
