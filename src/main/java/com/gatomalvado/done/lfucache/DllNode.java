package com.gatomalvado.done.lfucache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DllNode {

    private String key;
    private String value;
    private int freq;
    private DllNode next;
    private DllNode prev;

    public DllNode(String key, String value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }

    @Override
    public String toString() {
        return "Node: {" +
            "key: '" + key + '\'' +
            ", value: '" + value + '\'' +
            ", freq: " + freq +
            '}';
    }
}
