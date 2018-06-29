package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.domain.jacksonview.UserView;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity(name = "user")
public class User {
    @JsonView({UserView.overview.class,HumanResourceView.details.class,ProjectView.details.class,UserView.details.class})
    private int id;
    @JsonView({UserView.overview.class,UserView.details.class})
    private String username;
    private String password;
    @JsonView({UserView.overview.class,HumanResourceView.details.class,ProjectView.details.class,UserView.details.class})
    private String fullname;
    @JsonView({UserView.overview.class,UserView.details.class})
    private String email;
    @JsonView({UserView.overview.class,UserView.details.class})
    private String tel;
    @JsonView({UserView.overview.class,UserView.details.class})
    private Integer status;
    @JsonView(UserView.overview.class)
    private boolean isFirstLogin;
    @JsonView({UserView.overview.class,UserView.details.class})
    private int roleId;
    @JsonView({UserView.overview.class})
    private int companyId;
    private Collection<ChosenDomains> chosenDomainsById;
    private Collection<Feedback> feedbacksById;
    @JsonView(UserView.details.class)
    private Collection<HumanResource> humanResourcesById;
    private Collection<Notification> notificationsById;
    @JsonView(UserView.details.class)
    private Collection<Project> projectsById;
    private Role roleByRoleId;
    private Company companyByCompanyId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "fullname", nullable = true, length = 45)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 45)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "is_first_login", nullable = false)
    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }

    @Basic
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "company_id", nullable = false)
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isFirstLogin == user.isFirstLogin &&
                roleId == user.roleId &&
                companyId == user.companyId &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fullname, user.fullname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(tel, user.tel) &&
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, fullname, email, tel, status, isFirstLogin, roleId, companyId);
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ChosenDomains> getChosenDomainsById() {
        return chosenDomainsById;
    }

    public void setChosenDomainsById(Collection<ChosenDomains> chosenDomainsById) {
        this.chosenDomainsById = chosenDomainsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<HumanResource> getHumanResourcesById() {
        return humanResourcesById;
    }

    public void setHumanResourcesById(Collection<HumanResource> humanResourcesById) {
        this.humanResourcesById = humanResourcesById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Notification> getNotificationsById() {
        return notificationsById;
    }

    public void setNotificationsById(Collection<Notification> notificationsById) {
        this.notificationsById = notificationsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Project> getProjectsById() {
        return projectsById;
    }

    public void setProjectsById(Collection<Project> projectsById) {
        this.projectsById = projectsById;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }
}
