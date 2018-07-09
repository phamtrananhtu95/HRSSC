package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.SimilarView;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "similar_project", schema = "hrssc", catalog = "")
public class SimilarProject {
    @JsonView(SimilarView.Project.class)
    private int id;
    @JsonView(SimilarView.Project.class)
    private double similarScore;
    private int projectId;
    @JsonView(SimilarView.Project.class)
    private int similarProjectId;
    private Project projectByProjectId;
    @JsonView(SimilarView.Project.class)
    private Project projectBySimilarProjectId;

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
    @Column(name = "similar_score", nullable = false)
    public double getSimilarScore() {
        return similarScore;
    }

    public void setSimilarScore(double similarScore) {
        this.similarScore = similarScore;
    }

    @Basic
    @Column(name = "project_id", nullable = false)
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "similar_project_id", nullable = false)
    public int getSimilarProjectId() {
        return similarProjectId;
    }

    public void setSimilarProjectId(int similarProjectId) {
        this.similarProjectId = similarProjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimilarProject that = (SimilarProject) o;
        return id == that.id &&
                similarScore == that.similarScore &&
                projectId == that.projectId &&
                similarProjectId == that.similarProjectId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, similarScore, projectId, similarProjectId);
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "similar_project_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Project getProjectBySimilarProjectId() {
        return projectBySimilarProjectId;
    }

    public void setProjectBySimilarProjectId(Project projectBySimilarProjectId) {
        this.projectBySimilarProjectId = projectBySimilarProjectId;
    }
}
