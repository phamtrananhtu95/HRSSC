package com.example.HRSSC.models;

import javax.persistence.*;

/**
 * Created by Thien on 6/15/2018.
 */
@Entity
@Table(name = "resource_skills", schema = "hrssc", catalog = "")
public class ResourceSkills {
    private int id;
    private int skillId;
    private int humanResourceId;
    private Skill skillBySkillId;
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
    @Column(name = "skill_id", nullable = false)
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
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

        ResourceSkills that = (ResourceSkills) o;

        if (id != that.id) return false;
        if (skillId != that.skillId) return false;
        if (humanResourceId != that.humanResourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + skillId;
        result = 31 * result + humanResourceId;
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
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public HumanResource getHumanResourceByHumanResourceId() {
        return humanResourceByHumanResourceId;
    }

    public void setHumanResourceByHumanResourceId(HumanResource humanResourceByHumanResourceId) {
        this.humanResourceByHumanResourceId = humanResourceByHumanResourceId;
    }
}
