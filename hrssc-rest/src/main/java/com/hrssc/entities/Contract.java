package com.hrssc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Contract {
    private int id;
    private long createdDate;
    private Long acceptedDate;
    private long startDate;
    private long endDate;
    private int salary;
    private String additionalTerms;
    private boolean isAccepted;
    private Collection<Interaction> interactionsById;
    private Collection<Job> jobsById;

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
    @Column(name = "createdDate")
    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "acceptedDate")
    public Long getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Long acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    @Basic
    @Column(name = "startDate")
    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "endDate")
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
    @Column(name = "isAccepted")
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
}
