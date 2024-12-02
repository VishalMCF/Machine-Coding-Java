package com.gatomalvado.done.lfucache;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Dll {

    private DllNode head;
    private DllNode tail;
    private int currSize;

    public void addFront(DllNode node) {
        if(head == null) {
            head = node;
            tail = node;
            return;
        }
        node.setNext(head);
        head.setPrev(node);
        head = node;
    }

    public void removeNode(DllNode node) {
        DllNode prev = node.getPrev();
        DllNode next = node.getNext();
        if(node == tail) {
            // this is a tail node;
            if(prev == null) {
                // this is a head node too
                head = null;
                tail = null;
            } else {
                prev.setNext(null);
                tail = prev;
            }
        } else if(node == head) {
            // this is a head node;
            if(next == null) {
                // this is a tail node too;
                head = null;
                tail = null;
            } else {
                next.setPrev(null);
                head = next;
            }
        } else {
            if(prev != null) {
                prev.setNext(next);
            }
            if(next != null) {
                next.setPrev(prev);
            }
        }

        node.setPrev(null);
        node.setNext(null);
        this.currSize--;

    }

}
