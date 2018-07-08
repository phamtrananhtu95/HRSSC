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
    public double getBaseSalaryScore() {
        return baseSalaryScore;
    }
    public void setBaseSalaryScore(double baseSalaryScore) {
        this.baseSalaryScore = baseSalaryScore;
    }

    public ScoreRanker(double baseSkillScore, double baseDomainScore, double baseTypeScore, double similarityMultipler, double ratingMultipler) {
        this.baseSkillScore = baseSkillScore;
        this.baseDomainScore = baseDomainScore;
        this.baseTypeScore = baseTypeScore;
        this.similarityMultipler = similarityMultipler;
        this.ratingMultipler = ratingMultipler;
    }
    public ScoreRanker(){}


    private Map<ResourceSkills,ResourceSkills> findResourceSimilarSkills(HumanResource firstResource, HumanResource secondResource){
        Map<ResourceSkills,ResourceSkills> similarSkills = new HashMap<>();
        for(ResourceSkills skillA: firstResource.getResourceSkillsById()){
            for(ResourceSkills skillB:secondResource.getResourceSkillsById()){
                if(skillA.getSkillBySkillId().getTitle().equals(skillB.getSkillBySkillId().getTitle())){
                    similarSkills.put(skillA,skillB);
                }
            }
        }
        return similarSkills;
    }
    private double calculateResourceSkillScore(Map<ResourceSkills,ResourceSkills> skillsMap){
        double multipler = 1;
        if(skillsMap.size() == 0){
            return 0;
        }
        for(Map.Entry similarSkill: skillsMap.entrySet()){
            ResourceSkills firstSkill = (ResourceSkills)similarSkill.getKey();
            ResourceSkills secondSkill = (ResourceSkills)similarSkill.getValue();
            double different = Math.abs(firstSkill.getExperience() - secondSkill.getExperience());
            if(different == 0){
                multipler *=2.3;
            }
            if(different > 0 && different <= 0.5){
                multipler *= 1.7;
            }
            if(different > 0.5 && different <= 1){
                multipler *= 1.4;
            }
            if(different > 1 && different <= 1.5){
                multipler *= 1.2;
            }

        }
        return this.getBaseSkillScore() * multipler;
    }
    private double calculateResourceDomainScore(HumanResource firstResource, HumanResource secondResource){
        if(firstResource.getJobsById() == null||secondResource.getJobsById() == null){
            return 0;
        }
        double result = 0;
        for(Job jobA: firstResource.getJobsById()){
             for(Job jobB: secondResource.getJobsById()){
                 if(jobA.getProjectByProjectId().getDomain().equals(jobB.getProjectByProjectId().getDomain())){
                     result+= this.baseDomainScore;
                 }
             }

        }
        return result;
    }
    private double calculateResourceTypeScore(HumanResource firstResource, HumanResource secondResource){
        if(firstResource.getJobsById() == null||secondResource.getJobsById() == null){
            return 0;
        }
        double result = 0;
        for(Job jobA: firstResource.getJobsById()){
            for(Job jobB: secondResource.getJobsById()){
                if(jobA.getProjectByProjectId().getType().equals(jobB.getProjectByProjectId().getType())){
                    result+= this.baseTypeScore;
                }
            }

        }
        return result;
    }
    private double calculateResourceSalaryScore(HumanResource firstResource, HumanResource secondResource){
        return 0;
    }
    public double calculateResourceSimilarScore(HumanResource firstResource, HumanResource secondResource){
        double skill = calculateResourceSkillScore(findResourceSimilarSkills(firstResource,secondResource));
        if(skill == 0){
            return 0;
        }
        double type = calculateResourceTypeScore(firstResource,secondResource);
        double domain = calculateResourceDomainScore(firstResource,secondResource);
        double salary = calculateResourceSalaryScore(firstResource,secondResource);
        return skill + type + domain + salary;
    }



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
        if(skillMap.size() == 0){
            return 0;
        }
        for(Map.Entry similarEntry: skillMap.entrySet()){
            ResourceSkills resourceSkill = (ResourceSkills) similarEntry.getKey();
            SkillRequirements requirementSkill = (SkillRequirements) similarEntry.getValue();
            double temp = resourceSkill.getExperience() / requirementSkill.getExperience();
            if(temp == 1){
                multipler *=2.3;
            }
            if(temp > 1 && temp <= 1.3){
                multipler *= 1.7;
            }
            if(temp > 1.3 && temp <= 1.6){
                multipler *= 1.4;
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
           String rscDomain = job.getProjectByProjectId().getDomain();
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
            String rscDomain = job.getProjectByProjectId().getType();
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
        if(skill == 0){
            return 0;
        }
        double type = calculateTypeScore(resource,project);
        double domain = calculateDomainScore(resource,project);
        double salary = calculateSalaryScore();
        return skill + type + domain + salary;
    }
    public double rankingScore(HumanResource resource, Project project){
        double similarity = calculateSimilarityScore(resource,project);
        if(similarity == 0){
            return 0;
        }
        double rating = 0;
        return similarity * similarityMultipler + rating * ratingMultipler;
    }
}
