package com.hrssc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hibernate_sequence", schema = "hrssc", catalog = "")
public class HibernateSequence {
    private Long nextVal;
    private int id;

    @Basic
    @Column(name = "next_val")
    public Long getNextVal() {
        return nextVal;
    }

    public void setNextVal(Long nextVal) {
        this.nextVal = nextVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateSequence that = (HibernateSequence) o;
        return Objects.equals(nextVal, that.nextVal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nextVal);
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
