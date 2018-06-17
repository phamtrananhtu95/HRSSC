package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skill_requirements", schema = "hrssc", catalog = "")
public class SkillRequirements {
    private int id;
    private int skillId;
    private int projectId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Basic
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
        SkillRequirements that = (SkillRequirements) o;
        return id == that.id &&
                skillId == that.skillId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, skillId, projectId);
    }
}
