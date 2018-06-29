package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.MiscView;
import com.hrssc.domain.jacksonview.ProjectView;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Position {
    @JsonView({MiscView.ShortView.class,MiscView.FormInfo.class, ProjectView.ListView.class})
    private int id;
    @JsonView({MiscView.ShortView.class,MiscView.FormInfo.class, ProjectView.ListView.class,ProjectView.details.class})
    private String title;

    private Collection<ProjectRequirements> projectRequirementsById;

    @JsonView(MiscView.FormInfo.class)
    private Collection<Skill> skillsById;

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
        Position position = (Position) o;
        return id == position.id &&
                Objects.equals(title, position.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }


    @OneToMany(mappedBy = "positionByPositionId")
    public Collection<ProjectRequirements> getProjectRequirementsById() {
        return projectRequirementsById;
    }

    public void setProjectRequirementsById(Collection<ProjectRequirements> projectRequirementsById) {
        this.projectRequirementsById = projectRequirementsById;
    }

    @OneToMany(mappedBy = "positionByPositionId")
    public Collection<Skill> getSkillsById() {
        return skillsById;
    }

    public void setSkillsById(Collection<Skill> skillsById) {
        this.skillsById = skillsById;
    }
}
