package com.gatomalvado.done.trello.entities;

import java.util.Objects;
import java.util.Set;

import com.gatomalvado.done.trello.enums.EntityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskUser extends BaseEntity {

    private String id;
    private String email;
    private String name;
    private Set<String> boards;
    private EntityType entityType = EntityType.TASK_USER;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskUser taskUser = (TaskUser) o;
        return Objects.equals(id, taskUser.id);
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
