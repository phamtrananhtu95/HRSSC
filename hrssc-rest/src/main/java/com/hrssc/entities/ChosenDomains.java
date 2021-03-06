package com.hrssc.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chosen_domains", schema = "hrssc", catalog = "")
public class ChosenDomains {
    private int id;
    private String positions;
    private String locations;
    private String skills;
    private int userId;
    private User userByUserId;

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
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(positions, that.positions) &&
                Objects.equals(locations, that.locations) &&
                Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, positions, locations, skills, userId);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
