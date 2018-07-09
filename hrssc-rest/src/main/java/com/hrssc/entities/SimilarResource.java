package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.SimilarView;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "similar_resource", schema = "hrssc", catalog = "")
public class SimilarResource {
    @JsonView(SimilarView.Resource.class)
    private int id;
    @JsonView(SimilarView.Resource.class)
    private double similarScore;
    private int humanResourceId;
    @JsonView(SimilarView.Resource.class)
    private int similarResourceId;
    private HumanResource humanResourceByHumanResourceId;
    @JsonView(SimilarView.Resource.class)
    private HumanResource humanResourceBySimilarResourceId;

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
    @Column(name = "human_resource_id", nullable = false)
    public int getHumanResourceId() {
        return humanResourceId;
    }

    public void setHumanResourceId(int humanResourceId) {
        this.humanResourceId = humanResourceId;
    }

    @Basic
    @Column(name = "similar_resource_id", nullable = false)
    public int getSimilarResourceId() {
        return similarResourceId;
    }

    public void setSimilarResourceId(int similarResourceId) {
        this.similarResourceId = similarResourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimilarResource that = (SimilarResource) o;
        return id == that.id &&
                similarScore == that.similarScore &&
                humanResourceId == that.humanResourceId &&
                similarResourceId == that.similarResourceId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, similarScore, humanResourceId, similarResourceId);
    }

    @ManyToOne
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public HumanResource getHumanResourceByHumanResourceId() {
        return humanResourceByHumanResourceId;
    }

    public void setHumanResourceByHumanResourceId(HumanResource humanResourceByHumanResourceId) {
        this.humanResourceByHumanResourceId = humanResourceByHumanResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "similar_resource_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public HumanResource getHumanResourceBySimilarResourceId() {
        return humanResourceBySimilarResourceId;
    }

    public void setHumanResourceBySimilarResourceId(HumanResource humanResourceBySimilarResourceId) {
        this.humanResourceBySimilarResourceId = humanResourceBySimilarResourceId;
    }
}
