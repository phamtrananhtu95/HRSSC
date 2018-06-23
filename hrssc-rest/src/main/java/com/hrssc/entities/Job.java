package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Job {
    private int id;

    private int humanResourceId;

    private HumanResource humanResourceByHumanResourceId;
    private long joinedate;
    private long leaveDate;
    private int projectRequirementsId;
    private ProjectRequirements projectRequirementsByProjectRequirementsId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == job.id &&

                humanResourceId == job.humanResourceId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, humanResourceId);
    }


    @ManyToOne
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public HumanResource getHumanResourceByHumanResourceId() {
        return humanResourceByHumanResourceId;
    }

    public void setHumanResourceByHumanResourceId(HumanResource humanResourceByHumanResourceId) {
        this.humanResourceByHumanResourceId = humanResourceByHumanResourceId;
    }

    @Basic
    @Column(name = "joined_date")
    public long getJoinedate() {
        return joinedate;
    }

    public void setJoinedate(long joinedate) {
        this.joinedate = joinedate;
    }

    @Basic
    @Column(name = "leave_date")
    public long getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(long leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Basic
    @Column(name = "project_requirements_id")
    public int getProjectRequirementsId() {
        return projectRequirementsId;
    }

    public void setProjectRequirementsId(int projectRequirementsId) {
        this.projectRequirementsId = projectRequirementsId;
    }

    @ManyToOne
    @JoinColumn(name = "project_requirements_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public ProjectRequirements getProjectRequirementsByProjectRequirementsId() {
        return projectRequirementsByProjectRequirementsId;
    }

    public void setProjectRequirementsByProjectRequirementsId(ProjectRequirements projectRequirementsByProjectRequirementsId) {
        this.projectRequirementsByProjectRequirementsId = projectRequirementsByProjectRequirementsId;
    }
}
