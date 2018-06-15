package com.example.HRSSC.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Thien on 6/15/2018.
 */
@Entity
public class Project {
    private int id;
    private String title;
    private String description;
    private long createDate;
    private long endDate;
    private long duration;
    private String type;
    private String domain;
    private String processStatus;
    private String requestStatus;
    private int userId;
    private int companyId;
    private Collection<Interaction> interactionsById;
    private Collection<Job> jobsById;
    private Collection<Notification> notificationsById;
    private Collection<PositionRequirements> positionRequirementssById;
    private User userByUserId;
    private Company companyByCompanyId;
    private Collection<SkillRequirements> skillRequirementssById;

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

    @Basic
    @Column(name = "description", nullable = false, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "createDate", nullable = false)
    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "endDate", nullable = false)
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
    @Column(name = "processStatus", nullable = false, length = 45)
    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    @Basic
    @Column(name = "requestStatus", nullable = false, length = 45)
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
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

        if (id != project.id) return false;
        if (createDate != project.createDate) return false;
        if (endDate != project.endDate) return false;
        if (duration != project.duration) return false;
        if (userId != project.userId) return false;
        if (companyId != project.companyId) return false;
        if (title != null ? !title.equals(project.title) : project.title != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (type != null ? !type.equals(project.type) : project.type != null) return false;
        if (domain != null ? !domain.equals(project.domain) : project.domain != null) return false;
        if (processStatus != null ? !processStatus.equals(project.processStatus) : project.processStatus != null)
            return false;
        if (requestStatus != null ? !requestStatus.equals(project.requestStatus) : project.requestStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (createDate ^ (createDate >>> 32));
        result = 31 * result + (int) (endDate ^ (endDate >>> 32));
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (processStatus != null ? processStatus.hashCode() : 0);
        result = 31 * result + (requestStatus != null ? requestStatus.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + companyId;
        return result;
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
    public Collection<Notification> getNotificationsById() {
        return notificationsById;
    }

    public void setNotificationsById(Collection<Notification> notificationsById) {
        this.notificationsById = notificationsById;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<PositionRequirements> getPositionRequirementssById() {
        return positionRequirementssById;
    }

    public void setPositionRequirementssById(Collection<PositionRequirements> positionRequirementssById) {
        this.positionRequirementssById = positionRequirementssById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<SkillRequirements> getSkillRequirementssById() {
        return skillRequirementssById;
    }

    public void setSkillRequirementssById(Collection<SkillRequirements> skillRequirementssById) {
        this.skillRequirementssById = skillRequirementssById;
    }
}
