package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
public class Company {
    @JsonView({HumanResourceView.history.class,CompanyView.info.class,MatchingView.Resource.class,MatchingView.Project.class,ProjectView.details.class,HumanResourceView.details.class})
    private int id;
    @JsonView({HumanResourceView.history.class,CompanyView.info.class,InvitationView.ListView.class,MatchingView.Resource.class,MatchingView.Project.class,HumanResourceView.details.class,ProjectView.details.class})
    private String name;
    @JsonView(CompanyView.info.class)
    private String address;
    @JsonView({CompanyView.info.class,InvitationView.ListView.class,MatchingView.Project.class,HumanResourceView.details.class,ProjectView.details.class})
    private String city;
    @JsonView(CompanyView.info.class)
    private String tel;
    @JsonView(CompanyView.info.class)
    private String email;
    private int status;
    @JsonView(CompanyView.info.class)
    private String logo;
    private Collection<HumanResource> humanResourcesById;
    private Collection<Project> projectsById;
    private Collection<User> usersById;

    public Company() {

    }

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
    @Column(name = "tel", nullable = false, length = 45)
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
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "logo", nullable = true, length = 255)
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
        return id == company.id &&
                status == company.status &&
                Objects.equals(name, company.name) &&
                Objects.equals(address, company.address) &&
                Objects.equals(city, company.city) &&
                Objects.equals(tel, company.tel) &&
                Objects.equals(email, company.email) &&
                Objects.equals(logo, company.logo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address, city, tel, email, status, logo);
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
