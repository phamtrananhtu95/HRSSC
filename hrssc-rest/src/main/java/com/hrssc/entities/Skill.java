package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.domain.jacksonview.MatchingView;
import com.hrssc.domain.jacksonview.MiscView;
import com.hrssc.domain.jacksonview.ProjectView;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Skill {
    @JsonView({MiscView.ShortView.class,MiscView.FormInfo.class, ProjectView.ListView.class,HumanResourceView.overview.class})
    private int id;

    @JsonView({MiscView.ShortView.class,
            MiscView.FormInfo.class,
            ProjectView.ListView.class,
            MatchingView.Resource.class,
            HumanResourceView.overview.class})
    private String title;

    private Collection<ResourceSkills> resourceSkillsById;
    private Collection<SkillRequirements> skillRequirementsById;
    @JsonView(MiscView.ShortView.class)
    private int positionId;
    private Position positionByPositionId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "position_id")
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }
}
