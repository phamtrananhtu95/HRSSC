package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity(name = "human_resource")
@Table(name = "human_resource", schema = "hrssc", catalog = "")
public class HumanResource {
    @JsonView({HomeView.Resource.class,FeedbackView.projectFeedback.class,ApplianceView.Listview.class,InvitationView.ListView.class,HumanResourceView.overview.class, MatchingView.Project.class,UserView.details.class, HumanResourceView.details.class,ProjectView.details.class})
    private int id;
    @JsonView({HomeView.Resource.class,FeedbackView.resourceFeedback.class,FeedbackView.projectFeedback.class,SimilarView.Resource.class,JobView.JoinedResource.class,ApplianceView.Listview.class,InvitationView.ListView.class,HumanResourceView.overview.class, MatchingView.Project.class,UserView.details.class, HumanResourceView.details.class,ProjectView.details.class})
    private String fullname;
    @JsonView({NotificationView.class,HumanResourceView.overview.class,HumanResourceView.details.class,UserView.details.class})
    private int status;
    @JsonView({SimilarView.Resource.class,FeedbackView.resourceFeedback.class,JobView.JoinedResource.class,ApplianceView.Listview.class,InvitationView.ListView.class,HumanResourceView.overview.class,MatchingView.Project.class,UserView.details.class,HumanResourceView.details.class,ProjectView.details.class})
    private String email;
    @JsonView({SimilarView.Resource.class,HumanResourceView.overview.class,MatchingView.Project.class,UserView.details.class,HumanResourceView.details.class,ProjectView.details.class})
    private String tel;
    @JsonView({HomeView.Resource.class,HumanResourceView.overview.class,HumanResourceView.details.class})
    private Long availableDate;
    @JsonView({HomeView.Resource.class,HumanResourceView.overview.class,HumanResourceView.details.class})
    private Long availableDuration;
    @JsonView({HomeView.Resource.class,FeedbackView.resourceFeedback.class,FeedbackView.projectFeedback.class,SimilarView.Resource.class,JobView.JoinedResource.class,ApplianceView.Listview.class,HumanResourceView.overview.class,ProjectView.details.class})
    private int companyId;
    @JsonView({SimilarView.Resource.class,ApplianceView.Listview.class,InvitationView.ListView.class,MatchingView.Project.class,HumanResourceView.details.class})
    private double salary;


    private int userId;


    @JsonView({HomeView.Resource.class,FeedbackView.resourceFeedback.class,FeedbackView.projectFeedback.class,SimilarView.Resource.class,ApplianceView.Listview.class,JobView.JoinedResource.class,MatchingView.Resource.class,MatchingView.Project.class,HumanResourceView.details.class})
    private Company companyByCompanyId;

    @JsonView(HumanResourceView.details.class)
    private User userByUserId;

    @JsonView(InvitationView.ListView.class)
    private Collection<Interaction> interactionsById;

    @JsonView(HumanResourceView.details.class)
    private Collection<Job> jobsById;

    private Collection<Notification> notificationsById;
    @JsonView({HomeView.Resource.class,SimilarView.Resource.class,JobView.JoinedResource.class,MatchingView.Project.class,HumanResourceView.overview.class, HumanResourceView.details.class})
    private Collection<ResourceSkills> resourceSkillsById;
    private Collection<SimilarResource> similarResourcesById;
    private Collection<SimilarResource> similarResourcesById_0;
    
    @JsonView({HumanResourceView.details.class,HumanResourceView.history.class})
    private double avgRating;
    @JsonView(HumanResourceView.details.class)
    private Collection<AverageRating> averageRatingsById;

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

    @OneToMany(mappedBy = "humanResourceByHumanResourceId")
    public Collection<SimilarResource> getSimilarResourcesById() {
        return similarResourcesById;
    }

    public void setSimilarResourcesById(Collection<SimilarResource> similarResourcesById) {
        this.similarResourcesById = similarResourcesById;
    }

    @OneToMany(mappedBy = "humanResourceBySimilarResourceId")
    public Collection<SimilarResource> getSimilarResourcesById_0() {
        return similarResourcesById_0;
    }

    public void setSimilarResourcesById_0(Collection<SimilarResource> similarResourcesById_0) {
        this.similarResourcesById_0 = similarResourcesById_0;
    }



    @OneToMany(mappedBy = "humanResourceByHumanResourceId")
    public Collection<AverageRating> getAverageRatingsById() {
        return averageRatingsById;
    }

    public void setAverageRatingsById(Collection<AverageRating> averageRatingsById) {
        this.averageRatingsById = averageRatingsById;
    }
}
