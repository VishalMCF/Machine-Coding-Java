package com.gatomalvado.todo.trello.entities;

import java.util.Objects;
import java.util.Set;

import com.gatomalvado.todo.trello.enums.EntityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubGroup extends BaseEntity {
    private String id;
    private String boardId;
    private String name;
    private Set<Card> cards;

    private EntityType entityType = EntityType.SUBGROUP;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubGroup subGroup = (SubGroup) o;
        return Objects.equals(id, subGroup.id);
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
