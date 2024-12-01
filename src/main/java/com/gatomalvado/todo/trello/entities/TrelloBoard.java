package com.gatomalvado.todo.trello.entities;

import java.util.Objects;
import java.util.Set;

import com.gatomalvado.todo.trello.enums.EntityType;
import com.gatomalvado.todo.trello.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrelloBoard extends BaseEntity {
    // id, name, privacy (PUBLIC/PRIVATE), url, members, lists
    private String id;
    private String name;
    private String url;
    private Status status;
    private Set<TaskUser> taskMembers;
    private Set<SubGroup> subGroups;

    private EntityType entityType = EntityType.BOARD;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrelloBoard trelloBoard = (TrelloBoard) o;
        return Objects.equals(id, trelloBoard.id);
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
