package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contract_version", schema = "hrssc", catalog = "")
public class ContractVersion {
    private int id;
    private long dealDate;
    private long startDate;
    private long endDate;
    private int salary;
    private String additionalTerms;
    private int contractId;
    private Contract contractByContractId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "deal_date")
    public long getDealDate() {
        return dealDate;
    }

    public void setDealDate(long dealDate) {
        this.dealDate = dealDate;
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
    @Column(name = "contract_id")
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractVersion that = (ContractVersion) o;
        return id == that.id &&
                dealDate == that.dealDate &&
                startDate == that.startDate &&
                endDate == that.endDate &&
                salary == that.salary &&
                contractId == that.contractId &&
                Objects.equals(additionalTerms, that.additionalTerms);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dealDate, startDate, endDate, salary, additionalTerms, contractId);
    }

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Contract getContractByContractId() {
        return contractByContractId;
    }

    public void setContractByContractId(Contract contractByContractId) {
        this.contractByContractId = contractByContractId;
    }
}
