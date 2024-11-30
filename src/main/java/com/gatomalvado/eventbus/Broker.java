package com.gatomalvado.eventbus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Broker {

    private Map<String, Topic> topics;
    private Map<String, CopyOnWriteArrayList<Producer>> producers;

    public Broker() {
        this.topics = new ConcurrentHashMap<>();
        this.producers = new ConcurrentHashMap<>();
    }

    public void publish(String message, String topicName) {
        Topic topic = topics.get(topicName);
        topic.addElement(message);
    }

    public Topic subscribe(String topicName) {
        Topic topic = topics.get(topicName);
        if (topic == null) {
            throw new IllegalArgumentException("Topic " + topicName + " not found");
        }
        return this.topics.get(topicName);
    }

    public void createTopic(String topicName) {
        topics.putIfAbsent(topicName, new Topic(topicName));
    }

    public synchronized void registerProducer(String topicName, Producer producer) {
        if(!topics.containsKey(topicName)) {
            return;
        }
        CopyOnWriteArrayList<Producer> producerList = this.producers.computeIfAbsent(
            topicName,
            k -> new CopyOnWriteArrayList<>()
        );
        producerList.add(producer);
    }

    public synchronized void deRegisterProducer(String topicName, Producer producer) {
        CopyOnWriteArrayList<Producer> producerList = this.producers.get(topicName);
        if (producerList == null || producerList.size() == 0 || !producerList.contains(producer)) {
            return;
        }
        producerList.remove(producer);
        if(producerList.size() == 0) {
            Topic topic = topics.get(topicName);
            if(topic != null) {
                topic.closeTopic();
            }
        }
    }

}
