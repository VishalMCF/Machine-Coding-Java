package com.gatomalvado.done.eventbus;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Topic {

    private String topicName;
    private AtomicBoolean status;
    private List<String> elements;

    public Topic(String topicName) {
        this.topicName = topicName;
        this.status = new AtomicBoolean(false);
        this.elements = new CopyOnWriteArrayList<>();
    }

    public void addElement(String element) {
        elements.add(element);
    }

    public String getElement(int index) throws InterruptedException {
        synchronized (elements) {
            if(index < 0) {
                throw new IndexOutOfBoundsException();
            }
            while(index >= elements.size() && !status.get()) {
                elements.wait();
            }
            if(index >= elements.size() && status.get()) {
                return null;
            }
            return elements.get(index);
        }
    }

    public boolean isClosed() {
        return status.get();
    }

    public synchronized void closeTopic(){
        status.set(true);
        synchronized (elements) {
            elements.notifyAll();
        }
    }

}
