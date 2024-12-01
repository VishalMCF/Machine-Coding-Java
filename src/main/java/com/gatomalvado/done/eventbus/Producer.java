package com.gatomalvado.done.eventbus;

public class Producer {

    private final String producerName;

    private final Broker broker;

    public Producer(String producerName, Broker broker) {
        this.producerName = producerName;
        this.broker = broker;
    }

    public void publish(String message, String topicName) {
        broker.publish(message, topicName);
    }

    public void deregister(String topicName) {
        broker.deRegisterProducer(topicName, this);
    }

    public void register(String topicName) {
        broker.registerProducer(topicName, this);
    }
}
