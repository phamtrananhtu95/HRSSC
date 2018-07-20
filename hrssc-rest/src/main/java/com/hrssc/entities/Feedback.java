package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.FeedbackView;
import com.hrssc.domain.jacksonview.HumanResourceView;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Feedback {
    @JsonView({HumanResourceView.details.class,FeedbackView.loadAllview.class,HumanResourceView.history.class})
    private int id;
    @JsonView({HumanResourceView.details.class,FeedbackView.loadAllview.class,HumanResourceView.history.class})
    private double rating;
    @JsonView({HumanResourceView.details.class,FeedbackView.loadAllview.class,HumanResourceView.history.class})
    private String comment;
    @JsonView({HumanResourceView.details.class,FeedbackView.loadAllview.class,HumanResourceView.history.class})
    private long timestamp;
    @JsonView({HumanResourceView.details.class,FeedbackView.loadAllview.class,HumanResourceView.history.class})
    private int userId;
    @JsonView({HumanResourceView.details.class,FeedbackView.loadAllview.class,HumanResourceView.history.class})
    private User userByUserId;
    @JsonView(HumanResourceView.history.class)
    private Double jobKnowledge;
    @JsonView(HumanResourceView.history.class)
    private Double workQuality;
    @JsonView(HumanResourceView.history.class)
    private Double cooperation;
    @JsonView(HumanResourceView.history.class)
    private Double attendance;
    @JsonView(HumanResourceView.history.class)
    private Double workAttitude;
    private int jobId;
    private Job jobByJobId;



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
    @Column(name = "rating", nullable = false, precision = 0)
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = 200)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "timestamp", nullable = false)
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id == feedback.id &&
                Double.compare(feedback.rating, rating) == 0 &&
                timestamp == feedback.timestamp &&
                userId == feedback.userId &&
                Objects.equals(comment, feedback.comment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, rating, comment, timestamp, userId);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
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
    @Column(name = "job_id")
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Job getJobByJobId() {
        return jobByJobId;
    }

    public void setJobByJobId(Job jobByJobId) {
        this.jobByJobId = jobByJobId;
    }
}
