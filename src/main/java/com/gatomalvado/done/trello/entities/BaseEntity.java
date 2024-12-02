package com.gatomalvado.done.trello.entities;

public abstract class BaseEntity {
    public abstract String getEntityType();
    public abstract String getId();
    public abstract void printSelf();
}
