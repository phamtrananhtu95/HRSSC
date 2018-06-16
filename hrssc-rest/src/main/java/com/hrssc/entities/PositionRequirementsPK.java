package com.hrssc.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Thien on 6/16/2018.
 */
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

        if (positionId != that.positionId) return false;
        if (projectId != that.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = positionId;
        result = 31 * result + projectId;
        return result;
    }
}
