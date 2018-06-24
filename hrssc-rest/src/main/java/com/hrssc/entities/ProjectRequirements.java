package com.hrssc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "project_requirements", schema = "hrssc", catalog = "")
public class ProjectRequirements {
    private int id;
    private int payment;
    private int positionId;
    private int projectId;
    private boolean isAssigned;
    private Collection<Interaction> interactionsById;
    private Collection<Job> jobsById;
    private Position positionByPositionId;
    private Project projectByProjectId;
    private Collection<SkillRequirements> skillRequirementsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "payment")
    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    @Basic
    @Column(name = "position_id")
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "is_assigned")
    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRequirements that = (ProjectRequirements) o;
        return id == that.id &&
                payment == that.payment &&
                positionId == that.positionId &&
                projectId == that.projectId &&
                isAssigned == that.isAssigned;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, payment, positionId, projectId, isAssigned);
    }

    @OneToMany(mappedBy = "projectRequirementsByProjectRequirementsId")
    public Collection<Interaction> getInteractionsById() {
        return interactionsById;
    }

    public void setInteractionsById(Collection<Interaction> interactionsById) {
        this.interactionsById = interactionsById;
    }

    @OneToMany(mappedBy = "projectRequirementsByProjectRequirementsId")
    public Collection<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(Collection<Job> jobsById) {
        this.jobsById = jobsById;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @OneToMany(mappedBy = "projectRequirementsByProjectRequirementsId")
    public Collection<SkillRequirements> getSkillRequirementsById() {
        return skillRequirementsById;
    }

    public void setSkillRequirementsById(Collection<SkillRequirements> skillRequirementsById) {
        this.skillRequirementsById = skillRequirementsById;
    }
}
