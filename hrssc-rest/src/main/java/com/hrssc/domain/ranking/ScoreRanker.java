package com.hrssc.domain.ranking;

import com.hrssc.entities.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreRanker {
    private double baseSkillScore;
    private double baseDomainScore;
    private double baseTypeScore;
    private double baseSalaryScore;

    private double similarityMultipler;
    private double ratingMultipler;

    public double getSimilarityMultipler() {
        return similarityMultipler;
    }

    public void setSimilarityMultipler(double similarityMultipler) {
        this.similarityMultipler = similarityMultipler;
    }

    public double getRatingMultipler() {
        return ratingMultipler;
    }

    public void setRatingMultipler(double ratingMultipler) {
        this.ratingMultipler = ratingMultipler;
    }



    public double getBaseDomainScore() {
        return baseDomainScore;
    }

    public void setBaseDomainScore(double baseDomainScore) {
        this.baseDomainScore = baseDomainScore;
    }

    public double getBaseTypeScore() {
        return baseTypeScore;
    }

    public void setBaseTypeScore(double baseTypeScore) {
        this.baseTypeScore = baseTypeScore;
    }

    public double getBaseSkillScore() {
        return baseSkillScore;
    }

    public void setBaseSkillScore(double baseSkillScore) {
        this.baseSkillScore = baseSkillScore;
    }

    public ScoreRanker(double baseSkillScore, double baseDomainScore, double baseTypeScore, double similarityMultipler, double ratingMultipler) {
        this.baseSkillScore = baseSkillScore;
        this.baseDomainScore = baseDomainScore;
        this.baseTypeScore = baseTypeScore;
        this.similarityMultipler = similarityMultipler;
        this.ratingMultipler = ratingMultipler;
    }
    public ScoreRanker(){}




    private Map<ResourceSkills,SkillRequirements> findSimilarSkills(HumanResource resource, Project project){
        Map<ResourceSkills,SkillRequirements> skrMap = new HashMap<>();
        for(ProjectRequirements prjReq: project.getProjectRequirementsById()){
            for(SkillRequirements skr: prjReq.getSkillRequirementsById()){
                for(ResourceSkills rSkill: resource.getResourceSkillsById()){
                    if(skr.getSkillBySkillId().getTitle().equals(rSkill.getSkillBySkillId().getTitle())){
                        skrMap.put(rSkill,skr);
                    }
                }
            }
        }
        return skrMap;
    }

    private double calculateSkillScore(Map<ResourceSkills,SkillRequirements> skillMap){
        double multipler = 1;
        for(Map.Entry similarEntry: skillMap.entrySet()){
            ResourceSkills resourceSkill = (ResourceSkills) similarEntry.getKey();
            SkillRequirements requirementSkill = (SkillRequirements) similarEntry.getValue();
            double temp = resourceSkill.getExperience() / requirementSkill.getExperience();
            if(temp == 1){
                multipler *=2.5;
            }
            if(temp > 1 && temp <= 1.5){
                multipler *= 1.5;
            }
            if(temp < 1){
                multipler *= temp;
            }

        }
        return this.getBaseSkillScore() * multipler;
    }
    private double calculateDomainScore(HumanResource resource, Project project){
        if(resource.getJobsById() == null){
            return 0;
        }
        for(Job job: resource.getJobsById()){
           String rscDomain = job.getProjectRequirementsByProjectRequirementsId().getProjectByProjectId().getDomain();
           if(rscDomain.equals(project.getDomain())){
               return baseDomainScore;
           }
        }
        return 0;

    }
    private double calculateTypeScore(HumanResource resource, Project project){
        if(resource.getJobsById() == null){
            return 0;
        }
        for(Job job: resource.getJobsById()){
            String rscDomain = job.getProjectRequirementsByProjectRequirementsId().getProjectByProjectId().getType();
            if(rscDomain.equals(project.getType())){
                return baseTypeScore;
            }
        }
        return 0;
    }
    private double calculateSalaryScore(){
        return 0;
    }
    private double calculateSimilarityScore(HumanResource resource, Project project){
        double skill = calculateSkillScore(findSimilarSkills(resource,project));
        double type = calculateTypeScore(resource,project);
        double domain = calculateDomainScore(resource,project);
        double salary = calculateSalaryScore();
        return skill + type + domain + salary;
    }
    public double rankingScore(HumanResource resource, Project project){
        double similarity = calculateSimilarityScore(resource,project);
        double rating = 0;
        return similarity * similarityMultipler + rating * ratingMultipler;
    }
}
