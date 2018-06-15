package com.example.HRSSC.models;

import javax.persistence.*;

/**
 * Created by Thien on 6/15/2018.
 */
@Entity
public class Job {
    private int id;
    private Long timestamp;
    private int projectId;
    private int humanResourceId;
    private Project projectByProjectId;
    private HumanResource humanResourceByHumanResourceId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "timestamp", nullable = true)
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "project_id", nullable = false)
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "human_resource_id", nullable = false)
    public int getHumanResourceId() {
        return humanResourceId;
    }

    public void setHumanResourceId(int humanResourceId) {
        this.humanResourceId = humanResourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (id != job.id) return false;
        if (projectId != job.projectId) return false;
        if (humanResourceId != job.humanResourceId) return false;
        if (timestamp != null ? !timestamp.equals(job.timestamp) : job.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + projectId;
        result = 31 * result + humanResourceId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public HumanResource getHumanResourceByHumanResourceId() {
        return humanResourceByHumanResourceId;
    }

    public void setHumanResourceByHumanResourceId(HumanResource humanResourceByHumanResourceId) {
        this.humanResourceByHumanResourceId = humanResourceByHumanResourceId;
    }
}
