package com.hrssc.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.ApplianceView;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.domain.jacksonview.InvitationView;
import com.hrssc.domain.jacksonview.MatchingView;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Interaction {
    @JsonView({ApplianceView.Listview.class,InvitationView.ListView.class,MatchingView.Resource.class,MatchingView.Project.class})
    private int id;
    @JsonView({ApplianceView.Listview.class,InvitationView.ListView.class,MatchingView.Resource.class,MatchingView.Project.class})
    private String type;
    @JsonView({ApplianceView.Listview.class,MatchingView.Project.class})
    private int humanResourceId;
    @JsonView({ApplianceView.Listview.class,MatchingView.Project.class})
    private HumanResource humanResourceByHumanResourceId;
    @JsonView({MatchingView.Resource.class,MatchingView.Project.class})
    private Double rankingScore;
    @JsonView(MatchingView.Resource.class)
    private int projectId;
    @JsonView({InvitationView.ListView.class,MatchingView.Resource.class})
    private Project projectByProjectId;
    private Contract contractByContractId;

    public Interaction(String type){
        this.type = type;
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
    @Column(name = "type", nullable = false, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "human_resource_id", nullable = false)
    public int getHumanResourceId() {
        return humanResourceId;
    }

    public void setHumanResourceId(int humanResourceId) {
        this.humanResourceId = humanResourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interaction that = (Interaction) o;
        return id == that.id &&

                humanResourceId == that.humanResourceId &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type,humanResourceId);
    }

    @Basic
    @Column(name = "rankingScore")
    public Double getRankingScore() {
        return rankingScore;
    }

    public void setRankingScore(Double rankingScore) {
        this.rankingScore = rankingScore;
    }

    @Basic
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "human_resource_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public HumanResource getHumanResourceByHumanResourceId() {
        return humanResourceByHumanResourceId;
    }

    public void setHumanResourceByHumanResourceId(HumanResource humanResourceByHumanResourceId) {
        this.humanResourceByHumanResourceId = humanResourceByHumanResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    public Contract getContractByContractId() {
        return contractByContractId;
    }

    public void setContractByContractId(Contract contractByContractId) {
        this.contractByContractId = contractByContractId;
    }
}
