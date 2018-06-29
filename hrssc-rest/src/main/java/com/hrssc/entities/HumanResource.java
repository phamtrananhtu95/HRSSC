package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.domain.jacksonview.MatchingView;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.domain.jacksonview.UserView;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity
@Table(name = "human_resource", schema = "hrssc", catalog = "")
public class HumanResource {
    @JsonView({HumanResourceView.overview.class, MatchingView.Project.class,UserView.details.class, HumanResourceView.details.class,ProjectView.details.class})
    private int id;
    @JsonView({HumanResourceView.overview.class, MatchingView.Project.class,UserView.details.class, HumanResourceView.details.class,ProjectView.details.class})
    private String fullname;
    @JsonView({HumanResourceView.overview.class,HumanResourceView.details.class,UserView.details.class})
    private int status;
    @JsonView({HumanResourceView.overview.class,MatchingView.Project.class,UserView.details.class,HumanResourceView.details.class,ProjectView.details.class})
    private String email;
    @JsonView({HumanResourceView.overview.class,MatchingView.Project.class,UserView.details.class,HumanResourceView.details.class,ProjectView.details.class})
    private String tel;
    @JsonView({HumanResourceView.overview.class,HumanResourceView.details.class})
    private Long availableDate;
    @JsonView({HumanResourceView.overview.class,HumanResourceView.details.class})
    private Long availableDuration;
    @JsonView({HumanResourceView.overview.class,ProjectView.details.class})
    private int companyId;
    @JsonView({MatchingView.Project.class,HumanResourceView.details.class})
    private double salary;


    private int userId;
    @JsonView(HumanResourceView.details.class)
    private Collection<Feedback> feedbacksById;

    @JsonView({MatchingView.Resource.class,MatchingView.Project.class,HumanResourceView.details.class})
    private Company companyByCompanyId;

    @JsonView(HumanResourceView.details.class)
    private User userByUserId;

    private Collection<Interaction> interactionsById;

    @JsonView(HumanResourceView.details.class)
    private Collection<Job> jobsById;

    private Collection<Notification> notificationsById;
    @JsonView({MatchingView.Project.class,HumanResourceView.overview.class, HumanResourceView.details.class})
    private Collection<ResourceSkills> resourceSkillsById;

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
    @Column(name = "fullname", nullable = false, length = 45)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        return id == that.id &&
                status == that.status &&
                companyId == that.companyId &&
                userId == that.userId &&
                Objects.equals(fullname, that.fullname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(availableDate, that.availableDate) &&
                Objects.equals(availableDuration, that.availableDuration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fullname, status, email, tel, availableDate, availableDuration, companyId, userId);
    }

    @OneToMany(mappedBy = "humanResourceByHumanResourceId")
    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
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
    public Collection<ResourceSkills> getResourceSkillsById() {
        return resourceSkillsById;
    }

    public void setResourceSkillsById(Collection<ResourceSkills> resourceSkillsById) {
        this.resourceSkillsById = resourceSkillsById;
    }

    @Basic
    @Column(name = "salary", nullable = false, precision = 0)
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
