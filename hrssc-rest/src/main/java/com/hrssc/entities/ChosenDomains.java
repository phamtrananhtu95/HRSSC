package com.hrssc.entities;

import javax.persistence.*;

/**
 * Created by Thien on 6/16/2018.
 */
@Entity
@Table(name = "chosen_domains", schema = "hrssc", catalog = "")
public class ChosenDomains {
    private int id;
    private String positions;
    private String locations;
    private String skills;
    private int userId;
    private User userByUserId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "positions", nullable = true, length = 255)
    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    @Basic
    @Column(name = "locations", nullable = true, length = 255)
    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    @Basic
    @Column(name = "skills", nullable = true, length = 255)
    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChosenDomains that = (ChosenDomains) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (positions != null ? !positions.equals(that.positions) : that.positions != null) return false;
        if (locations != null ? !locations.equals(that.locations) : that.locations != null) return false;
        if (skills != null ? !skills.equals(that.skills) : that.skills != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (positions != null ? positions.hashCode() : 0);
        result = 31 * result + (locations != null ? locations.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
