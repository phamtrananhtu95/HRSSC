package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resource_skills", schema = "hrssc", catalog = "")
public class ResourceSkills {
    private int id;
    private int skillId;
    private int humanResourceId;

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
    @Column(name = "human_resource_id")
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
        ResourceSkills that = (ResourceSkills) o;
        return id == that.id &&
                skillId == that.skillId &&
                humanResourceId == that.humanResourceId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, skillId, humanResourceId);
    }
}
