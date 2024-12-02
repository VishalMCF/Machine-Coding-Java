package com.gatomalvado.done.lfucache;

import java.util.HashMap;
import java.util.Map;

public class LfuCache {

    private int maxCap;
    private Map<String, DllNode> storage;
    private Map<Integer, Dll> keyToListMap;
    private int minFreq;

    public LfuCache(int maxCap) {
        this.maxCap = maxCap;
        storage = new HashMap<>();
        this.keyToListMap = new HashMap<>();
    }

    public String get(String key) {
        DllNode node = storage.get(key);
        if (node == null) {
            System.out.println("no value exists for the key: "+key);
            return null;
        }
        updateKey(node);
        return node.getValue();
    }

    public void put(String key, String value) {
        if(keyToListMap.containsKey(key)) {
            DllNode node = storage.get(key);
            node.setValue(value);
            updateKey(node);
        } else {
            if(this.storage.size() == this.maxCap) {
                Dll minFreqList = this.keyToListMap.get(this.minFreq);
                DllNode tailNode = minFreqList.getTail();
                if(minFreqList != null) {
                    minFreqList.removeNode(tailNode);
                }
                if(minFreqList.getCurrSize() == 0) {
                    this.keyToListMap.remove(this.minFreq);
                }
                this.storage.remove(tailNode.getKey());
            }

            DllNode newNode = new DllNode(key, value);
            storage.put(key, newNode);
            this.minFreq = 1;
            Dll newList = this.keyToListMap.get(this.minFreq);
            if(newList == null) {
                newList = new Dll();
            }
            newList.addFront(newNode);
            this.keyToListMap.put(this.minFreq, newList);
        }
    }

    public void updateKey(DllNode node) {
        int currFreq = node.getFreq();
        node.setFreq(currFreq + 1);
        Dll prevList = keyToListMap.get(currFreq);
        prevList.removeNode(node);
        Dll newList = keyToListMap.get(node.getFreq());
        if(newList == null) {
            newList = new Dll();
        }
        newList.addFront(node);
        keyToListMap.put(node.getFreq(), newList);
        if(prevList.getCurrSize() == 0) {
            keyToListMap.remove(currFreq);
            this.minFreq = node.getFreq();
        }
    }

    public void printCache() {
        System.out.println(storage);
    }
}
