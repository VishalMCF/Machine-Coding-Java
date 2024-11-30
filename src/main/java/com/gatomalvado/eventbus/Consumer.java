package com.gatomalvado.eventbus;

public class Consumer {

    private final String consumerId;
    private int offset;
    private final Broker broker;
    private Topic topic;

    public Consumer(String consumerId, Broker broker) {
        this.consumerId = consumerId;
        this.offset = 0;
        this.broker = broker;
    }

    public void startConsumer() throws InterruptedException {
        if(topic==null) {
            throw new RuntimeException("topic is not subscribed yet");
        }
        while(true) {
            String element = topic.getElement(offset);
            if(element==null) {
                break;
            }
            System.out.println(this.consumerId + " : consumed " + topic.getElement(offset));
            offset += 1;
        }
    }

    public void subscribeToTopic(String topicName) {
        Topic topic = broker.subscribe(topicName);
        if(topic==null) {
            throw new RuntimeException("topic does not exist");
        }
        this.topic = topic;
        this.offset = 0;
    }
}
