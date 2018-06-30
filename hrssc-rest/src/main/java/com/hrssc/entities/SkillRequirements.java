package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.MatchingView;
import com.hrssc.domain.jacksonview.ProjectView;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skill_requirements", schema = "hrssc", catalog = "")
public class SkillRequirements {
    @JsonView({ProjectView.ListView.class,ProjectView.details.class})
    private int id;

    @JsonView(ProjectView.ListView.class)
    private int skillId;

    @JsonView({ProjectView.ListView.class,MatchingView.Resource.class,ProjectView.Summary.class,ProjectView.details.class})
    private Skill skillBySkillId;
    @JsonView({ProjectView.ListView.class,ProjectView.Summary.class,ProjectView.details.class,MatchingView.Resource.class})
    private double experience;

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
    @Column(name = "skill_id", nullable = false)
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }


    @Basic
    @Column(name = "project_requirements_id", nullable = false)
    public int getProjectRequirementsId() {
        return projectRequirementsId;
    }

    public void setProjectRequirementsId(int skillId) {
        this.projectRequirementsId = skillId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillRequirements that = (SkillRequirements) o;
        return id == that.id &&
                skillId == that.skillId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, skillId);
    }

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Skill getSkillBySkillId() {
        return skillBySkillId;
    }

    public void setSkillBySkillId(Skill skillBySkillId) {
        this.skillBySkillId = skillBySkillId;
    }


    @Basic
    @Column(name = "experience")
    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }



    @ManyToOne
    @JoinColumn(name = "project_requirements_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public ProjectRequirements getProjectRequirementsByProjectRequirementsId() {
        return projectRequirementsByProjectRequirementsId;
    }

    public void setProjectRequirementsByProjectRequirementsId(ProjectRequirements projectRequirementsByProjectRequirementsId) {
        this.projectRequirementsByProjectRequirementsId = projectRequirementsByProjectRequirementsId;
    }
}
