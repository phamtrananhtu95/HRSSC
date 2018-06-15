package com.example.HRSSC.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Thien on 6/15/2018.
 */
@Entity
public class Company {
    private int id;
    private String title;
    private String description;
    private long createDate;
    private long endDate;
    private String type;
    private int processStatus;
    private int status;
    private long duration;
    private Collection<HumanResource> humanResourcesById;
    private Collection<Project> projectsById;
    private Collection<User> usersById;

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
    @Column(name = "type", nullable = false, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "processStatus", nullable = false)
    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
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
    @Column(name = "duration", nullable = false)
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != company.id) return false;
        if (createDate != company.createDate) return false;
        if (endDate != company.endDate) return false;
        if (processStatus != company.processStatus) return false;
        if (status != company.status) return false;
        if (duration != company.duration) return false;
        if (title != null ? !title.equals(company.title) : company.title != null) return false;
        if (description != null ? !description.equals(company.description) : company.description != null) return false;
        if (type != null ? !type.equals(company.type) : company.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (createDate ^ (createDate >>> 32));
        result = 31 * result + (int) (endDate ^ (endDate >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + processStatus;
        result = 31 * result + status;
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "companyByCompanyId")
    public Collection<HumanResource> getHumanResourcesById() {
        return humanResourcesById;
    }

    public void setHumanResourcesById(Collection<HumanResource> humanResourcesById) {
        this.humanResourcesById = humanResourcesById;
    }

    @OneToMany(mappedBy = "companyByCompanyId")
    public Collection<Project> getProjectsById() {
        return projectsById;
    }

    public void setProjectsById(Collection<Project> projectsById) {
        this.projectsById = projectsById;
    }

    @OneToMany(mappedBy = "companyByCompanyId")
    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }
}
