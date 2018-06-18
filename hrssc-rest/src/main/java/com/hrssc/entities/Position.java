package com.hrssc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Position {
    private int id;
    private String title;
    private String level;
    private Collection<HumanResource> humanResourcesById;
    private Collection<PositionRequirements> positionRequirementsById;

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
        return id == position.id &&
                Objects.equals(title, position.title) &&
                Objects.equals(level, position.level);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, level);
    }

    @OneToMany(mappedBy = "positionByPositionId")
    public Collection<HumanResource> getHumanResourcesById() {
        return humanResourcesById;
    }

    public void setHumanResourcesById(Collection<HumanResource> humanResourcesById) {
        this.humanResourcesById = humanResourcesById;
    }

    @OneToMany(mappedBy = "positionByPositionId")
    public Collection<PositionRequirements> getPositionRequirementsById() {
        return positionRequirementsById;
    }

    public void setPositionRequirementsById(Collection<PositionRequirements> positionRequirementsById) {
        this.positionRequirementsById = positionRequirementsById;
    }
}
