package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "average_rating", schema = "hrssc", catalog = "")
public class AverageRating {
    private int id;
    private Double jobKnowledge;
    private Double workQuality;
    private Double cooperation;
    private Double attendance;
    private Double workAttitude;
    private Double avgRating;
    private int humanResourceId;
    private HumanResource humanResourceByHumanResourceId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_knowledge")
    public Double getJobKnowledge() {
        return jobKnowledge;
    }

    public void setJobKnowledge(Double jobKnowledge) {
        this.jobKnowledge = jobKnowledge;
    }

    @Basic
    @Column(name = "work_quality")
    public Double getWorkQuality() {
        return workQuality;
    }

    public void setWorkQuality(Double workQuality) {
        this.workQuality = workQuality;
    }

    @Basic
    @Column(name = "cooperation")
    public Double getCooperation() {
        return cooperation;
    }

    public void setCooperation(Double cooperation) {
        this.cooperation = cooperation;
    }

    @Basic
    @Column(name = "attendance")
    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    @Basic
    @Column(name = "work_attitude")
    public Double getWorkAttitude() {
        return workAttitude;
    }

    public void setWorkAttitude(Double workAttitude) {
        this.workAttitude = workAttitude;
    }

    @Basic
    @Column(name = "avg_rating")
    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
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
        AverageRating that = (AverageRating) o;
        return id == that.id &&
                humanResourceId == that.humanResourceId &&
                Objects.equals(jobKnowledge, that.jobKnowledge) &&
                Objects.equals(workQuality, that.workQuality) &&
                Objects.equals(cooperation, that.cooperation) &&
                Objects.equals(attendance, that.attendance) &&
                Objects.equals(workAttitude, that.workAttitude) &&
                Objects.equals(avgRating, that.avgRating);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jobKnowledge, workQuality, cooperation, attendance, workAttitude, avgRating, humanResourceId);
    }

    @ManyToOne
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public HumanResource getHumanResourceByHumanResourceId() {
        return humanResourceByHumanResourceId;
    }

    public void setHumanResourceByHumanResourceId(HumanResource humanResourceByHumanResourceId) {
        this.humanResourceByHumanResourceId = humanResourceByHumanResourceId;
    }
}
