package com.mingzhi.demo;

/**
 * 懒汉式单例模式下线程不安全解决例子
 */
public class LazySingletonDemo {

    //volatile 第二层锁 防止指令重排序
    private static volatile LazySingletonDemo lazySingletonDemo = null;

    private LazySingletonDemo(){}

    public static LazySingletonDemo getInstance() {
        if(lazySingletonDemo==null){
            //第一层锁
            synchronized (LazySingletonDemo.class){
                //双层验证 防止多线程中线程通过第一次判空后 被第一个进入的线程阻塞 后面进入时lazySingletonDemo任然是null 导致再去new
                if(lazySingletonDemo==null){
                    lazySingletonDemo = new LazySingletonDemo();
                }
            }
        }
        return lazySingletonDemo;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(getInstance().toString());
                }
            }).start();
        }
    }
}
