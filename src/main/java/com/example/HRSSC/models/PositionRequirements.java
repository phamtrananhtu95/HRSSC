package com.example.HRSSC.models;

import javax.persistence.*;

/**
 * Created by Thien on 6/15/2018.
 */
@Entity
@Table(name = "position_requirements", schema = "hrssc", catalog = "")
public class PositionRequirements {
    private int id;
    private int positionId;
    private int projectId;
    private Position positionByPositionId;
    private Project projectByProjectId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "position_id", nullable = false)
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "project_id", nullable = false)
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

        if (id != that.id) return false;
        if (positionId != that.positionId) return false;
        if (projectId != that.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + positionId;
        result = 31 * result + projectId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }
}
