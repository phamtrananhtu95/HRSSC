package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "temporary_info", schema = "hrssc", catalog = "")
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
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_address")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Basic
    @Column(name = "company_city")
    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    @Basic
    @Column(name = "company_country")
    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    @Basic
    @Column(name = "company_tax")
    public String getCompanyTax() {
        return companyTax;
    }

    public void setCompanyTax(String companyTax) {
        this.companyTax = companyTax;
    }

    @Basic
    @Column(name = "company_email")
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Basic
    @Column(name = "company_tel")
    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    @Basic
    @Column(name = "representative_name")
    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    @Basic
    @Column(name = "representtative_email")
    public String getRepresenttativeEmail() {
        return representtativeEmail;
    }

    public void setRepresenttativeEmail(String representtativeEmail) {
        this.representtativeEmail = representtativeEmail;
    }

    @Basic
    @Column(name = "representative_tel")
    public String getRepresentativeTel() {
        return representativeTel;
    }

    public void setRepresentativeTel(String representativeTel) {
        this.representativeTel = representativeTel;
    }

    @Basic
    @Column(name = "representative_title")
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
