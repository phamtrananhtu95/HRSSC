package com.hrssc.entities;

import javax.persistence.*;

/**
 * Created by Thien on 6/16/2018.
 */
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

        if (id != that.id) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (companyAddress != null ? !companyAddress.equals(that.companyAddress) : that.companyAddress != null)
            return false;
        if (companyCity != null ? !companyCity.equals(that.companyCity) : that.companyCity != null) return false;
        if (companyCountry != null ? !companyCountry.equals(that.companyCountry) : that.companyCountry != null)
            return false;
        if (companyTax != null ? !companyTax.equals(that.companyTax) : that.companyTax != null) return false;
        if (companyEmail != null ? !companyEmail.equals(that.companyEmail) : that.companyEmail != null) return false;
        if (companyTel != null ? !companyTel.equals(that.companyTel) : that.companyTel != null) return false;
        if (representativeName != null ? !representativeName.equals(that.representativeName) : that.representativeName != null)
            return false;
        if (representtativeEmail != null ? !representtativeEmail.equals(that.representtativeEmail) : that.representtativeEmail != null)
            return false;
        if (representativeTel != null ? !representativeTel.equals(that.representativeTel) : that.representativeTel != null)
            return false;
        if (representativeTitle != null ? !representativeTitle.equals(that.representativeTitle) : that.representativeTitle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyAddress != null ? companyAddress.hashCode() : 0);
        result = 31 * result + (companyCity != null ? companyCity.hashCode() : 0);
        result = 31 * result + (companyCountry != null ? companyCountry.hashCode() : 0);
        result = 31 * result + (companyTax != null ? companyTax.hashCode() : 0);
        result = 31 * result + (companyEmail != null ? companyEmail.hashCode() : 0);
        result = 31 * result + (companyTel != null ? companyTel.hashCode() : 0);
        result = 31 * result + (representativeName != null ? representativeName.hashCode() : 0);
        result = 31 * result + (representtativeEmail != null ? representtativeEmail.hashCode() : 0);
        result = 31 * result + (representativeTel != null ? representativeTel.hashCode() : 0);
        result = 31 * result + (representativeTitle != null ? representativeTitle.hashCode() : 0);
        return result;
    }
}
