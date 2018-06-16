package com.hrssc.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Thien on 6/16/2018.
 */
@Entity
@Table(name = "human_resource", schema = "hrssc", catalog = "")
public class HumanResource {
    private int id;
    private String fullname;
    private String status;
    private String email;
    private String tel;
    private Long availableDate;
    private Long availableDuration;
    private int companyId;
    private int positionId;
    private int userId;
    private Collection<Feedback> feedbacksById;
    private Company companyByCompanyId;
    private Position positionByPositionId;
    private User userByUserId;
    private Collection<Interaction> interactionsById;
    private Collection<Job> jobsById;
    private Collection<Notification> notificationsById;
    private Collection<ResourceSkills> resourceSkillssById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fullname", nullable = false, length = 45)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 45)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "available_date", nullable = true)
    public Long getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Long availableDate) {
        this.availableDate = availableDate;
    }

    @Basic
    @Column(name = "available_duration", nullable = true)
    public Long getAvailableDuration() {
        return availableDuration;
    }

    public void setAvailableDuration(Long availableDuration) {
        this.availableDuration = availableDuration;
    }

    @Basic
    @Column(name = "company_id", nullable = false)
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "position_id", nullable = false)
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
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

        HumanResource that = (HumanResource) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (positionId != that.positionId) return false;
        if (userId != that.userId) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (availableDate != null ? !availableDate.equals(that.availableDate) : that.availableDate != null)
            return false;
        if (availableDuration != null ? !availableDuration.equals(that.availableDuration) : that.availableDuration != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (availableDate != null ? availableDate.hashCode() : 0);
        result = 31 * result + (availableDuration != null ? availableDuration.hashCode() : 0);
        result = 31 * result + companyId;
        result = 31 * result + positionId;
        result = 31 * result + userId;
        return result;
    }

    @OneToMany(mappedBy = "humanResourceByHumanResourceId")
    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "humanResourceByHumanResourceId")
    public Collection<Interaction> getInteractionsById() {
        return interactionsById;
    }

    public void setInteractionsById(Collection<Interaction> interactionsById) {
        this.interactionsById = interactionsById;
    }

    @OneToMany(mappedBy = "humanResourceByHumanResourceId")
    public Collection<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(Collection<Job> jobsById) {
        this.jobsById = jobsById;
    }

    @OneToMany(mappedBy = "humanResourceByHumanResourceId")
    public Collection<Notification> getNotificationsById() {
        return notificationsById;
    }

    public void setNotificationsById(Collection<Notification> notificationsById) {
        this.notificationsById = notificationsById;
    }

    @OneToMany(mappedBy = "humanResourceByHumanResourceId")
    public Collection<ResourceSkills> getResourceSkillssById() {
        return resourceSkillssById;
    }

    public void setResourceSkillssById(Collection<ResourceSkills> resourceSkillssById) {
        this.resourceSkillssById = resourceSkillssById;
    }
}
