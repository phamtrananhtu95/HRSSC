package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "human_resource", schema = "hrssc", catalog = "")
public class HumanResource {
    private int id;
    private String fullname;
    private int status;
    private String email;
    private String tel;
    private Long availableDate;
    private Long availableDuration;
    private int companyId;
    private int positionId;
    private int userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "available_date")
    public Long getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Long availableDate) {
        this.availableDate = availableDate;
    }

    @Basic
    @Column(name = "available_duration")
    public Long getAvailableDuration() {
        return availableDuration;
    }

    public void setAvailableDuration(Long availableDuration) {
        this.availableDuration = availableDuration;
    }

    @Basic
    @Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "position_id")
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "user_id")
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
                positionId == that.positionId &&
                userId == that.userId &&
                Objects.equals(fullname, that.fullname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(availableDate, that.availableDate) &&
                Objects.equals(availableDuration, that.availableDuration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fullname, status, email, tel, availableDate, availableDuration, companyId, positionId, userId);
    }
}
