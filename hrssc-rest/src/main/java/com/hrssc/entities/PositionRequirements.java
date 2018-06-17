package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "position_requirements", schema = "hrssc", catalog = "")
@IdClass(PositionRequirementsPK.class)
public class PositionRequirements {
    private int positionId;
    private int projectId;

    @Id
    @Column(name = "position_id")
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Id
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionRequirements that = (PositionRequirements) o;
        return positionId == that.positionId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(positionId, projectId);
    }
}
