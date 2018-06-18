package com.hrssc.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PositionRequirementsPK implements Serializable {
    private int positionId;
    private int projectId;

    @Column(name = "position_id", nullable = false)
    @Id
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Column(name = "project_id", nullable = false)
    @Id
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
        PositionRequirementsPK that = (PositionRequirementsPK) o;
        return positionId == that.positionId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(positionId, projectId);
    }
}
