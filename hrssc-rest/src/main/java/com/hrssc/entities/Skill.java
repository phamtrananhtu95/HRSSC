package com.hrssc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Skill {
    private int id;
    private String title;
    private Collection<ResourceSkills> resourceSkillsById;
    private Collection<SkillRequirements> skillRequirementsById;

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
        return id == skill.id &&
                Objects.equals(title, skill.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }

    @OneToMany(mappedBy = "skillBySkillId")
    public Collection<ResourceSkills> getResourceSkillsById() {
        return resourceSkillsById;
    }

    public void setResourceSkillsById(Collection<ResourceSkills> resourceSkillsById) {
        this.resourceSkillsById = resourceSkillsById;
    }

    @OneToMany(mappedBy = "skillBySkillId")
    public Collection<SkillRequirements> getSkillRequirementsById() {
        return skillRequirementsById;
    }

    public void setSkillRequirementsById(Collection<SkillRequirements> skillRequirementsById) {
        this.skillRequirementsById = skillRequirementsById;
    }
}
