package com.hrssc.entities;

import javax.persistence.*;

/**
 * Created by Thien on 6/16/2018.
 */
@Entity
@Table(name = "skill_requirements", schema = "hrssc", catalog = "")
public class SkillRequirements {
    private int id;
    private int skillId;
    private int projectId;
    private Skill skillBySkillId;
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
    @Column(name = "skill_id", nullable = false)
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
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

        SkillRequirements that = (SkillRequirements) o;

        if (id != that.id) return false;
        if (skillId != that.skillId) return false;
        if (projectId != that.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + skillId;
        result = 31 * result + projectId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Skill getSkillBySkillId() {
        return skillBySkillId;
    }

    public void setSkillBySkillId(Skill skillBySkillId) {
        this.skillBySkillId = skillBySkillId;
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
