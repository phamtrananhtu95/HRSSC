package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.ContractView;
import com.hrssc.domain.jacksonview.FeedbackView;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.domain.jacksonview.JobView;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Job {
    @JsonView({JobView.JoinedResource.class,FeedbackView.projectFeedback.class,FeedbackView.resourceFeedback.class,ContractView.overview.class})
    private int id;
    @JsonView({JobView.JoinedResource.class,FeedbackView.projectFeedback.class,FeedbackView.resourceFeedback.class,ContractView.overview.class})
    private int humanResourceId;

    @JsonView({JobView.JoinedResource.class,FeedbackView.projectFeedback.class,FeedbackView.resourceFeedback.class,ContractView.overview.class})
    private HumanResource humanResourceByHumanResourceId;
    @JsonView({JobView.JoinedResource.class,HumanResourceView.history.class,FeedbackView.projectFeedback.class})
    private long joinedate;
    private long leaveDate;

    @JsonView({HumanResourceView.history.class,JobView.JoinedResource.class,ContractView.overview.class})
    private int projectId;
    @JsonView({HumanResourceView.history.class,ContractView.overview.class})
    private Project projectByProjectId;
    @JsonView(ContractView.overview.class)
    private int status;
    @JsonView(ContractView.overview.class)
    private Contract contractByContractId;
    @JsonView(ContractView.overview.class)
    private Integer contractId;
    private Collection<Feedback> feedbacksById;

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
    @Column(name = "human_resource_id", nullable = false)
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
        Job job = (Job) o;
        return id == job.id &&

                humanResourceId == job.humanResourceId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, humanResourceId);
    }

    @Basic
    @Column(name = "joined_date")
    public long getJoinedate() {
        return joinedate;
    }

    public void setJoinedate(long joinedate) {
        this.joinedate = joinedate;
    }

    @Basic
    @Column(name = "leave_date")
    public long getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(long leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Basic
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public HumanResource getHumanResourceByHumanResourceId() {
        return humanResourceByHumanResourceId;
    }

    public void setHumanResourceByHumanResourceId(HumanResource humanResourceByHumanResourceId) {
        this.humanResourceByHumanResourceId = humanResourceByHumanResourceId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id", updatable = false, insertable = false)
    public Contract getContractByContractId() {
        return contractByContractId;
    }

    public void setContractByContractId(Contract contractByContractId) {
        this.contractByContractId = contractByContractId;
    }

    @Basic
    @Column(name = "contract_id")
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    @OneToMany(mappedBy = "jobByJobId")
    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }
}
