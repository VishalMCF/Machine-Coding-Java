package com.gatomalvado.splitwise.model;

public enum ExpenseType {
    EQUAL("equal"),
    EXACT("exact"),
    PERCENT("percent");

    private String name;

    ExpenseType(String name) {
        this.name = name;
    }
}
