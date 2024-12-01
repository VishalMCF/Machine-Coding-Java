package com.gatomalvado.done.trello.enums;

public enum EntityType {
    BOARD("board"),
    TASK_USER("user"),
    CARD("card"),
    SUBGROUP("list");

    private final String value;

    EntityType(String value) {
        this.value = value;
    }

}
