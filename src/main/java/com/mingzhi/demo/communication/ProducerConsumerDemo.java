package com.mingzhi.demo.communication;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Medium medium = new Medium();


        new Thread(new Consumer(medium)).start();
        new Thread(new Consumer(medium)).start();
        new Thread(new Consumer(medium)).start();

        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();

    }



    static class Producer implements Runnable{

        private Medium medium;

        public Producer(Medium medium){
            this.medium = medium;
        }
        @Override
        public void run() {
            medium.put();
        }
    }

    static class Consumer implements Runnable{

        private Medium medium;

        public Consumer(Medium medium){
            this.medium = medium;
        }
        @Override
        public void run() {
            medium.get();
        }
    }

    static class Medium{
        private int num = 0;
        private int max = 20;

        public synchronized void put(){
            while (true){
                //判断是否已满，如果满了通知生产者停止生产
                if(num >= max){
                    System.out.println("库存已满");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    //没满，继续生产
                    System.out.println("生产中 当前库存："+ ++num);
                    notifyAll();
                }
            }
        }

        public synchronized void get(){
            while (true) {
                //判断是否有库存，有库存，继续获取
                if (num > 0) {
                    System.out.println("消费中 当前库存：" + --num);
                    notifyAll();
                } else {
                    //如果没有则通知消费者停止获取
                    System.out.println("无库存");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
