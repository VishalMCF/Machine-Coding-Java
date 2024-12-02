package com.gatomalvado.done.lfucache;

public class Main {

    public static void main(String[] args) {
        LfuCache lfuCache = new LfuCache(4);
        lfuCache.put("k1", "5");
        lfuCache.printCache();
        lfuCache.put("k2", "1");
        lfuCache.printCache();
        lfuCache.put("k3", "2");
        lfuCache.printCache();
        lfuCache.put("k4", "4");
        lfuCache.printCache();
        lfuCache.put("k5", "8");
        lfuCache.printCache();
        lfuCache.get("k1");
        lfuCache.printCache();
        lfuCache.put("k4", "8");
        lfuCache.printCache();
        lfuCache.put("k9", "8");
        lfuCache.printCache();
        lfuCache.put("k10", "8");
        lfuCache.printCache();
        lfuCache.get("k2");
        lfuCache.printCache();
        lfuCache.get("k3");
        lfuCache.printCache();
        lfuCache.get("k4");
        lfuCache.printCache();
        lfuCache.get("k1");
        lfuCache.printCache();
    }
}
