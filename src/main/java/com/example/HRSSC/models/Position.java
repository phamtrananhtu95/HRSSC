package com.example.HRSSC.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Thien on 6/15/2018.
 */
@Entity
public class Position {
    private int id;
    private String title;
    private String level;
    private Collection<HumanResource> humanResourcesById;
    private Collection<PositionRequirements> positionRequirementssById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "level", nullable = false, length = 45)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (id != position.id) return false;
        if (title != null ? !title.equals(position.title) : position.title != null) return false;
        if (level != null ? !level.equals(position.level) : position.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "positionByPositionId")
    public Collection<HumanResource> getHumanResourcesById() {
        return humanResourcesById;
    }

    public void setHumanResourcesById(Collection<HumanResource> humanResourcesById) {
        this.humanResourcesById = humanResourcesById;
    }

    @OneToMany(mappedBy = "positionByPositionId")
    public Collection<PositionRequirements> getPositionRequirementssById() {
        return positionRequirementssById;
    }

    public void setPositionRequirementssById(Collection<PositionRequirements> positionRequirementssById) {
        this.positionRequirementssById = positionRequirementssById;
    }
}
