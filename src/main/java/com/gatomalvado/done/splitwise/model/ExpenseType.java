package com.gatomalvado.done.splitwise.model;

public enum ExpenseType {
    EQUAL("equal"),
    EXACT("exact"),
    PERCENT("percent");

    private String name;

    ExpenseType(String name) {
        this.name = name;
    }
}
