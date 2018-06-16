package com.hrssc.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Thien on 6/16/2018.
 */
@Entity
public class Company {
    private int id;
    private String name;
    private String address;
    private String city;
    private String tel;
    private String email;
    private int status;
    private String logo;
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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "tel", nullable = false)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "logo", nullable = false, length = 255)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != company.id) return false;
        if (tel != company.tel) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (address != null ? !address.equals(company.address) : company.address != null) return false;
        if (city != null ? !city.equals(company.city) : company.city != null) return false;
        if (email != null ? !email.equals(company.email) : company.email != null) return false;
        if (status !=company.status) return false;
        if (logo != null ? !logo.equals(company.logo) : company.logo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (tel !=null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status ^ (status >>>32));
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
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
