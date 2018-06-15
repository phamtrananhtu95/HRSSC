package com.example.HRSSC.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Thien on 6/15/2018.
 */
@Entity
public class Permission {
    private int id;
    private String title;
    private Collection<RolePermissions> rolePermissionssById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
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

        Permission that = (Permission) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "permissionByPermissionId")
    public Collection<RolePermissions> getRolePermissionssById() {
        return rolePermissionssById;
    }

    public void setRolePermissionssById(Collection<RolePermissions> rolePermissionssById) {
        this.rolePermissionssById = rolePermissionssById;
    }
}
