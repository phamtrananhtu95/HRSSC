package com.hrssc.entities;


import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.ApplianceView;
import com.hrssc.domain.jacksonview.ContractView;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Contract {
    @JsonView({ApplianceView.ContractView.class,ContractView.overview.class,ContractView.detail.class})
    private int id;
    @JsonView({ApplianceView.ContractView.class,ContractView.overview.class,ContractView.detail.class})
    private long createdDate;
    @JsonView(ContractView.detail.class)
    private Long acceptedDate;
    @JsonView({ApplianceView.ContractView.class,ContractView.overview.class,ContractView.detail.class})
    private long startDate;
    @JsonView({ApplianceView.ContractView.class,ContractView.overview.class,ContractView.detail.class})
    private long endDate;
    @JsonView({ApplianceView.ContractView.class,ContractView.overview.class,ContractView.detail.class})
    private int salary;
    @JsonView({ApplianceView.ContractView.class,ContractView.detail.class})
    private String additionalTerms;
    private boolean isAccepted;
    private Collection<Interaction> interactionsById;
    private Collection<Job> jobsById;
    @JsonView({ApplianceView.ContractView.class,ContractView.detail.class})
    private Integer latestEditorId;
    @JsonView(ApplianceView.ContractView.class)
    private User userByLatestEditorId;
    private Collection<ChatLog> chatLogsById;
    private Collection<ContractVersion> contractVersionsById;

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
    @Column(name = "created_date")
    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "accepted_date")
    public Long getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Long acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    @Basic
    @Column(name = "start_date")
    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "salary")
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "additional_terms")
    public String getAdditionalTerms() {
        return additionalTerms;
    }

    public void setAdditionalTerms(String additionalTerms) {
        this.additionalTerms = additionalTerms;
    }

    @Basic
    @Column(name = "is_accepted")
    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id &&
                createdDate == contract.createdDate &&
                startDate == contract.startDate &&
                endDate == contract.endDate &&
                salary == contract.salary &&
                isAccepted == contract.isAccepted &&
                Objects.equals(acceptedDate, contract.acceptedDate) &&
                Objects.equals(additionalTerms, contract.additionalTerms);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createdDate, acceptedDate, startDate, endDate, salary, additionalTerms, isAccepted);
    }

    @OneToMany(mappedBy = "contractByContractId")
    public Collection<Interaction> getInteractionsById() {
        return interactionsById;
    }

    public void setInteractionsById(Collection<Interaction> interactionsById) {
        this.interactionsById = interactionsById;
    }

    @OneToMany(mappedBy = "contractByContractId")
    public Collection<Job> getJobsById() {
        return jobsById;
    }

    public void setJobsById(Collection<Job> jobsById) {
        this.jobsById = jobsById;
    }

    @Basic
    @Column(name = "latest_editor_id", nullable = true)
    public Integer getLatestEditorId() {
        return latestEditorId;
    }

    public void setLatestEditorId(Integer latestEditorId) {
        this.latestEditorId = latestEditorId;
    }

    @ManyToOne
    @JoinColumn(name = "latest_editor_id", referencedColumnName = "id", updatable = false, insertable = false)
    public User getUserByLatestEditorId() {
        return userByLatestEditorId;
    }

    public void setUserByLatestEditorId(User userByLatestEditorId) {
        this.userByLatestEditorId = userByLatestEditorId;
    }

    @OneToMany(mappedBy = "contractByContractId")
    public Collection<ChatLog> getChatLogsById() {
        return chatLogsById;
    }

    public void setChatLogsById(Collection<ChatLog> chatLogsById) {
        this.chatLogsById = chatLogsById;
    }

    @OneToMany(mappedBy = "contractByContractId")
    public Collection<ContractVersion> getContractVersionsById() {
        return contractVersionsById;
    }

    public void setContractVersionsById(Collection<ContractVersion> contractVersionsById) {
        this.contractVersionsById = contractVersionsById;
    }
}
