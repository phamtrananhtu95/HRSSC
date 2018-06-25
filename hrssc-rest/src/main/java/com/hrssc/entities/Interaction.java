package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Interaction {
    private int id;
    private String type;
    private int humanResourceId;
    private HumanResource humanResourceByHumanResourceId;
    private int projectRequirementsId;
    private ProjectRequirements projectRequirementsByProjectRequirementsId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        Interaction that = (Interaction) o;
        return id == that.id &&

                humanResourceId == that.humanResourceId &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type,humanResourceId);
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
