package com.mingzhi.demo;

/**
 *sync different scene
 */
public class SyncSceneDemo {

    // 修饰普通方法：锁住这个实例
    private synchronized void scene1(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 修饰静态方法：锁住整个类的所有实例
    private static synchronized void scene2(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 修饰代码块：锁住括号中的对象
    private Object lock = new Object();
    private void scene3(){
        System.out.println("before lock");
        synchronized (lock){
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
    }

    private static void test3() {
        SyncSceneDemo syncSceneDemo1 = new SyncSceneDemo();
        SyncSceneDemo syncSceneDemo2 = new SyncSceneDemo();

        // 仅实例的对象被锁住 重新new的实例不会被锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncSceneDemo1.scene3();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncSceneDemo1.scene3();
            }
        }).start();
    }

    private static void test2() {
        SyncSceneDemo syncSceneDemo1 = new SyncSceneDemo();
        SyncSceneDemo syncSceneDemo2 = new SyncSceneDemo();

        // syncSceneDemo1和2都被锁住 依次运行
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncSceneDemo1.scene2();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncSceneDemo2.scene2();
            }
        }).start();
    }

    private static void test1(){
        SyncSceneDemo syncSceneDemo1 = new SyncSceneDemo();
        SyncSceneDemo syncSceneDemo2 = new SyncSceneDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncSceneDemo1.scene1();
            }
        }).start();
        // syncSceneDemo1被锁住 依次运行
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncSceneDemo1.scene1();
            }
        }).start();
        //syncSceneDemo2 没有被锁和第一个线程同时运行
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncSceneDemo2.scene1();
            }
        }).start();
    }
}
