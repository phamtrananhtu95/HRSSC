package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Project {
    @JsonView({HomeView.Project.class,FeedbackView.projectFeedback.class,ApplianceView.Listview.class,InvitationView.ListView.class,ProjectView.ListView.class, MatchingView.Resource.class,ProjectView.details.class,UserView.details.class})
    private int id;
    @JsonView({HomeView.Project.class,FeedbackView.projectFeedback.class,SimilarView.Project.class,HumanResourceView.history.class,ApplianceView.Listview.class,InvitationView.ListView.class,ProjectView.ListView.class,MatchingView.Resource.class,ProjectView.Summary.class,ProjectView.details.class,UserView.details.class})
    private String title;
    @JsonView({ApplianceView.Listview.class,ProjectView.ListView.class,ProjectView.details.class})
    private String description;
    @JsonView({HomeView.Project.class,ApplianceView.Listview.class,InvitationView.ListView.class,ProjectView.ListView.class,ProjectView.details.class,UserView.details.class})
    private long createDate;
    @JsonView({HomeView.Project.class,SimilarView.Project.class,ApplianceView.Listview.class,InvitationView.ListView.class,ProjectView.ListView.class,MatchingView.Resource.class,ProjectView.details.class,UserView.details.class})
    private long endDate;
    @JsonView({SimilarView.Project.class,ApplianceView.Listview.class,InvitationView.ListView.class,ProjectView.ListView.class,MatchingView.Resource.class,ProjectView.details.class})
    private long duration;
    @JsonView({SimilarView.Project.class,HumanResourceView.history.class,ApplianceView.Listview.class,ProjectView.ListView.class,MatchingView.Resource.class,ProjectView.Summary.class,ProjectView.details.class,UserView.details.class})
    private String type;
    @JsonView({SimilarView.Project.class,HumanResourceView.history.class,ApplianceView.Listview.class,ProjectView.ListView.class,MatchingView.Resource.class,ProjectView.Summary.class,ProjectView.details.class,UserView.details.class})
    private String domain;
    @JsonView({ApplianceView.Listview.class,ProjectView.ListView.class,ProjectView.details.class,UserView.details.class})
    private int processStatus;
    @JsonView({ApplianceView.Listview.class,ProjectView.ListView.class,UserView.details.class})
    private int requestStatus;
    @JsonView({ProjectView.details.class,ProjectView.ListView.class})
    private int userId;
    @JsonView({HomeView.Project.class,SimilarView.Project.class,ProjectView.ListView.class,HumanResourceView.history.class})
    private int companyId;
    private Collection<Notification> notificationsById;
    private User userByUserId;
    @JsonView({HomeView.Project.class,SimilarView.Project.class,HumanResourceView.history.class,InvitationView.ListView.class,MatchingView.Resource.class,MatchingView.Project.class,ProjectView.details.class})
    private Company companyByCompanyId;

    @JsonView({HomeView.Project.class,SimilarView.Project.class,ProjectView.ListView.class,MatchingView.Resource.class,ProjectView.Summary.class,ProjectView.details.class})
    private Collection<ProjectRequirements> projectRequirementsById;
    @JsonView(ApplianceView.Listview.class)
    private Collection<Interaction> interactionsById;
    @JsonView(FeedbackView.projectFeedback.class)
    private Collection<Job> jobsById;
    private Collection<SimilarProject> similarProjectsById;
    private Collection<SimilarProject> similarProjectsById_0;

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
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "create_date", nullable = false)
    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "duration", nullable = false)
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "domain", nullable = false, length = 45)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "process_status", nullable = false)
    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    @Basic
    @Column(name = "request_status", nullable = false)
    public int getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(int requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "company_id", nullable = false)
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                createDate == project.createDate &&
                endDate == project.endDate &&
                duration == project.duration &&
                processStatus == project.processStatus &&
                requestStatus == project.requestStatus &&
                userId == project.userId &&
                companyId == project.companyId &&
                Objects.equals(title, project.title) &&
                Objects.equals(description, project.description) &&
                Objects.equals(type, project.type) &&
                Objects.equals(domain, project.domain);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, createDate, endDate, duration, type, domain, processStatus, requestStatus, userId, companyId);
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<Notification> getNotificationsById() {
        return notificationsById;
    }

    public void setNotificationsById(Collection<Notification> notificationsById) {
        this.notificationsById = notificationsById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<ProjectRequirements> getProjectRequirementsById() {
        return projectRequirementsById;
    }

    public void setProjectRequirementsById(Collection<ProjectRequirements> projectRequirementsById) {
        this.projectRequirementsById = projectRequirementsById;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<Interaction> getInteractionsById() {
        return interactionsById;
    }

    public void setInteractionsById(Collection<Interaction> interactionsById) {
        this.interactionsById = interactionsById;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(Collection<Job> jobsById) {
        this.jobsById = jobsById;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<SimilarProject> getSimilarProjectsById() {
        return similarProjectsById;
    }

    public void setSimilarProjectsById(Collection<SimilarProject> similarProjectsById) {
        this.similarProjectsById = similarProjectsById;
    }

    @OneToMany(mappedBy = "projectBySimilarProjectId")
    public Collection<SimilarProject> getSimilarProjectsById_0() {
        return similarProjectsById_0;
    }

    public void setSimilarProjectsById_0(Collection<SimilarProject> similarProjectsById_0) {
        this.similarProjectsById_0 = similarProjectsById_0;
    }
}
