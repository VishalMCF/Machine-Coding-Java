package com.gatomalvado.done.trello.entities;

import java.util.Objects;

import com.gatomalvado.done.trello.enums.EntityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card extends BaseEntity {
    // id, name, description, assigned user
    private String id;
    private String name;
    private String description;
    private TaskUser assignedUser;
    private SubGroup subGroup;

    private EntityType entityType = EntityType.CARD;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String getEntityType() {
        return this.entityType.name();
    }

    @Override
    public void printSelf() {
        System.out.println(this);
    }
}
