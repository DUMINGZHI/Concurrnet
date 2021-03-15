package com.mingzhi.demo.communication;

/**
 *每个线程自己的一个存在于内存中的变量
 */
public class ThreadLocalDemo {
    static ThreadLocal threadLocal = new ThreadLocal();

    static void print() {
        //打印当前线程中本地内存中本地变量的值
        System.out.println("之前" + threadLocal.get());
        //清除本地内存中的本地变量
        threadLocal.remove();
     }
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(1);
                print();
                System.out.println("之后" + threadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("ok");
                print();
                System.out.println("之后" + threadLocal.get());
            }
        }).start();
    }
}
