package com.gatomalvado.done.eventbus;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Pub sub kafka");

        // create kafka broker
        Broker broker = new Broker();
        broker.createTopic("topicA");
        broker.createTopic("topicB");

        Producer producer1 = new Producer("P_1 ", broker);
        Producer producer2 = new Producer("P_2 ", broker);

        Consumer consumer1 = new Consumer("C_1 ", broker);
        Consumer consumer2 = new Consumer("C_2 ", broker);
        Consumer consumer3 = new Consumer("C_3 ", broker);
        Consumer consumer4 = new Consumer("C_4 ", broker);
        Consumer consumer5 = new Consumer("C_5 ", broker);



        Thread pt1 = new Thread(() -> {
            producer1.register("topicA");
            producer1.register("topicB");
            for(int i=1; i<=3; i++) {
                if(i%2 == 0) {
                    producer1.publish("message_"+i, "topicA");
                } else {
                    producer1.publish("message_"+i, "topicB");
                }
            }
            producer1.deregister("topicA");
            producer1.deregister("topicB");
        });

        Thread pt2 = new Thread(() -> {
            producer2.register("topicA");
            producer2.register("topicB");
            for(int i=4; i<=7; i++) {
                if(i%2 == 0) {
                    producer2.publish("message_"+i, "topicA");
                } else {
                    producer2.publish("message_"+i, "topicB");
                }
            }
            producer2.deregister("topicA");
            producer2.deregister("topicB");
        });

        Thread ct1 = new Thread(() -> {
            consumer1.subscribeToTopic("topicA");
            try {
                consumer1.startConsumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread ct2 = new Thread(() -> {
            consumer2.subscribeToTopic("topicA");
            try {
                consumer2.startConsumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread ct3 = new Thread(() -> {
            consumer3.subscribeToTopic("topicA");
            try {
                consumer3.startConsumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread ct4 = new Thread(() -> {
            consumer4.subscribeToTopic("topicA");
            try {
                consumer4.startConsumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread ct5 = new Thread(() -> {
            consumer5.subscribeToTopic("topicA");
            try {
                consumer5.startConsumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        ct1.start(); ct2.start(); ct3.start(); ct4.start(); ct5.start();
        pt1.start(); pt2.start();

        ct1.join(); ct2.join(); ct3.join(); ct4.join(); ct5.join();
        pt1.join(); pt2.join();

        Thread ct11 = new Thread(() -> {
            consumer1.subscribeToTopic("topicB");
            try{
                consumer1.startConsumer();
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread ct12 = new Thread(() -> {
            consumer2.subscribeToTopic("topicB");
            try{
                consumer2.startConsumer();
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread ct13 = new Thread(() -> {
            consumer3.subscribeToTopic("topicB");
            try{
                consumer3.startConsumer();
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread ct14 = new Thread(() -> {
            consumer4.subscribeToTopic("topicB");
            try{
                consumer4.startConsumer();
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread ct15 = new Thread(() -> {
            consumer5.subscribeToTopic("topicB");
            try{
                consumer5.startConsumer();
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        ct11.start(); ct12.start(); ct13.start(); ct14.start(); ct15.start();
        ct11.join(); ct12.join(); ct13.join(); ct14.join();
    }
}