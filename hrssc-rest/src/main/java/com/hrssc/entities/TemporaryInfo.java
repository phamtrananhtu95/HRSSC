package com.hrssc.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Entity
@Table(name = "temporary_info", schema = "hrssc", catalog = "")
@Builder
@AllArgsConstructor
public class TemporaryInfo {
    private int id;
    private String companyName;
    private String companyAddress;
    private String companyCity;
    private String companyCountry;
    private String companyTax;
    private String companyEmail;
    private String companyTel;
    private String representativeName;
    private String representtativeEmail;
    private String representativeTel;
    private String representativeTitle;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_name", nullable = false, length = 45)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_address", nullable = false, length = 45)
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Basic
    @Column(name = "company_city", nullable = false, length = 45)
    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    @Basic
    @Column(name = "company_country", nullable = false, length = 45)
    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    @Basic
    @Column(name = "company_tax", nullable = false, length = 45)
    public String getCompanyTax() {
        return companyTax;
    }

    public void setCompanyTax(String companyTax) {
        this.companyTax = companyTax;
    }

    @Basic
    @Column(name = "company_email", nullable = false, length = 45)
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Basic
    @Column(name = "company_tel", nullable = false, length = 45)
    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    @Basic
    @Column(name = "representative_name", nullable = false, length = 45)
    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    @Basic
    @Column(name = "representtative_email", nullable = false, length = 45)
    public String getRepresenttativeEmail() {
        return representtativeEmail;
    }

    public void setRepresenttativeEmail(String representtativeEmail) {
        this.representtativeEmail = representtativeEmail;
    }

    @Basic
    @Column(name = "representative_tel", nullable = false, length = 45)
    public String getRepresentativeTel() {
        return representativeTel;
    }

    public void setRepresentativeTel(String representativeTel) {
        this.representativeTel = representativeTel;
    }

    @Basic
    @Column(name = "representative_title", nullable = false, length = 45)
    public String getRepresentativeTitle() {
        return representativeTitle;
    }

    public void setRepresentativeTitle(String representativeTitle) {
        this.representativeTitle = representativeTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemporaryInfo that = (TemporaryInfo) o;
        return id == that.id &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(companyAddress, that.companyAddress) &&
                Objects.equals(companyCity, that.companyCity) &&
                Objects.equals(companyCountry, that.companyCountry) &&
                Objects.equals(companyTax, that.companyTax) &&
                Objects.equals(companyEmail, that.companyEmail) &&
                Objects.equals(companyTel, that.companyTel) &&
                Objects.equals(representativeName, that.representativeName) &&
                Objects.equals(representtativeEmail, that.representtativeEmail) &&
                Objects.equals(representativeTel, that.representativeTel) &&
                Objects.equals(representativeTitle, that.representativeTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, companyName, companyAddress, companyCity, companyCountry, companyTax, companyEmail, companyTel, representativeName, representtativeEmail, representativeTel, representativeTitle);
    }
}
