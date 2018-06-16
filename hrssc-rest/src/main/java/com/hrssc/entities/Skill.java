package com.hrssc.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Thien on 6/16/2018.
 */
@Entity
public class Skill {
    private int id;
    private String title;
    private Collection<ResourceSkills> resourceSkillssById;
    private Collection<SkillRequirements> skillRequirementssById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (id != skill.id) return false;
        if (title != null ? !title.equals(skill.title) : skill.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "skillBySkillId")
    public Collection<ResourceSkills> getResourceSkillssById() {
        return resourceSkillssById;
    }

    public void setResourceSkillssById(Collection<ResourceSkills> resourceSkillssById) {
        this.resourceSkillssById = resourceSkillssById;
    }

    @OneToMany(mappedBy = "skillBySkillId")
    public Collection<SkillRequirements> getSkillRequirementssById() {
        return skillRequirementssById;
    }

    public void setSkillRequirementssById(Collection<SkillRequirements> skillRequirementssById) {
        this.skillRequirementssById = skillRequirementssById;
    }
}
