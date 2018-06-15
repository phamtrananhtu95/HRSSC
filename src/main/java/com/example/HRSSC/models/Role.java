package com.example.HRSSC.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Thien on 6/15/2018.
 */
@Entity
public class Role {
    private int id;
    private String title;
    private Collection<RolePermissions> rolePermissionssById;
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
    @Column(name = "title", nullable = false, length = 20)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (title != null ? !title.equals(role.title) : role.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<RolePermissions> getRolePermissionssById() {
        return rolePermissionssById;
    }

    public void setRolePermissionssById(Collection<RolePermissions> rolePermissionssById) {
        this.rolePermissionssById = rolePermissionssById;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }
}
