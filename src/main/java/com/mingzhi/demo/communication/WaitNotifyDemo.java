package com.mingzhi.demo.communication;

/**
 * wait/notify/notifyAll的例子: 一个线程受到另一个线程状态改变的影响
 * 注意事项：wait和notify都要使用synchronized锁住
 */
public class WaitNotifyDemo {

    private static volatile boolean flag = false;
    private static Object object = new Object();

    private void simulatePass(){
        while(!flag){
            System.out.println("等待下载完毕后传送文件");
            synchronized (object){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("开始传送文件");
    }

    private void afterDownload(){
        synchronized (object){
            System.out.println("下载完毕");
            flag = true;
            //object.notifyAll();
            object.notify();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new WaitNotifyDemo().simulatePass();
            }
        }).start();

//2个线程用于测试notifAll()
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                new WaitNotifyDemo().simulatePass();
//            }
//        }).start();

        Thread.sleep(2000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new WaitNotifyDemo().afterDownload();
            }
        }).start();
    }
}
