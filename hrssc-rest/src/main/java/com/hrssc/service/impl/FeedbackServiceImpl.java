package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.FeedbackService;
import javassist.NotFoundException;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Math;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HumanResourceRepository humanResourceRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    AverageRatingRepository averageRatingRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ProjectRequirementRepository projectRequirementRepository;

    @PersistenceContext
    EntityManager entityManager;

    Comparator<Feedback> dateComparator = new Comparator<Feedback>() {
        @Override
        public int compare(Feedback f1, Feedback f2) {
            if(f2.getTimestamp() > f1.getTimestamp()){
                return 1;
            }
            if(f2.getTimestamp() < f1.getTimestamp()){
                return -1;
            }

            return 0;
        }
    };

    public List<Feedback> loadAllFeedback(int resourceId){
        List<Job> jobList = jobRepository.findByHumanResourceId(resourceId);
        List<Feedback> resultList = new ArrayList<>();
        for (Job jobtmp: jobList) {
            List<Feedback> feedbackList = (List<Feedback>) jobtmp.getFeedbacksById();
            if(!feedbackList.isEmpty()){
                Contract contract = contractRepository.findById((int)jobtmp.getContractId());
                long duration = (contract.getEndDate() - contract.getStartDate())/86400000;
                jobtmp.setJoinedate(duration);
                resultList.addAll(feedbackList);

            }
        }
        resultList.sort(dateComparator);
        return resultList;
    }

    @Transactional
    public Feedback addFeedback (Feedback feedback) throws Exception{
        Job checkjob = jobRepository.findById(feedback.getJobId());
        if(checkjob == null || checkjob.getStatus() == Constant.JobStatus.PENDING || checkjob.getStatus() == Constant.JobStatus.ON_GOING){
            throw new NotFoundException("Job not exist or not yet finish! ");
        //    return "Job not exist or not yet finish!";
        }
        HumanResource humanResource = humanResourceRepository.getById(checkjob.getHumanResourceId());
        if (humanResource == null){
            throw new NotFoundException("Resource not exist!");
        //    return "Resource not exist!";
        }
        //Tinh rating +save feedback
        double rating = (feedback.getJobKnowledge()*3 + feedback.getWorkQuality()*3 + feedback.getCooperation()*2
                        +feedback.getAttendance() + feedback.getWorkAttitude())/10 ;
       feedback.setRating(rating);
        long timestamp = System.currentTimeMillis();
        feedback.setTimestamp(timestamp);
        feedbackRepository.save(feedback);
        return feedback;
      }

    public String calculateAvrRating(int resourceId, Feedback feedback){
        HumanResource humanResource = humanResourceRepository.getById(resourceId);
        List<Job> humanResourceJobList = jobRepository.findByHumanResourceIdAndStatus(humanResource.getId(), Constant.JobStatus.FINISHED);
        List<Job> calcujobList = new ArrayList<>();
        for (Job tmp: humanResourceJobList) {
            if(!tmp.getFeedbacksById().isEmpty()) {
                calcujobList.add(tmp);
            }
        }

        AverageRating averageRating = averageRatingRepository.findByHumanResourceId(humanResource.getId());
        if(averageRating == null){
            averageRating = new AverageRating();
            averageRating.setHumanResourceId(humanResource.getId());
        }
        if(calcujobList == null){
            return "SumTinWong!";
        }
        if (calcujobList.size() == 1){
            averageRating.setJobKnowledge(feedback.getJobKnowledge());
            averageRating.setWorkQuality(feedback.getWorkQuality());
            averageRating.setCooperation(feedback.getCooperation());
            averageRating.setAttendance(feedback.getAttendance());
            averageRating.setWorkAttitude(feedback.getWorkAttitude());
            averageRating.setAvgRating(feedback.getRating());
            averageRatingRepository.save(averageRating);
            return "Success!";
        }
        long sumDuration = 0;
        float sumJK = 0;
        float sumWQ = 0;
        float sumCo = 0;
        float sumAt = 0;
        float sumWA = 0;
        for (Job tmp : calcujobList) {
            Feedback tmpFb = feedbackRepository.findByJobId(tmp.getId());
            Contract tmpContract = contractRepository.findById((int)tmp.getContractId());
            long tmpduration = (tmpContract.getEndDate() - tmpContract.getStartDate())/86400000;
            sumDuration += tmpduration;
            sumJK += tmpFb.getJobKnowledge()* tmpduration;
            sumWQ += tmpFb.getWorkQuality()* tmpduration;
            sumCo += tmpFb.getCooperation()* tmpduration;
            sumAt += tmpFb.getAttendance()* tmpduration;
            sumWA += tmpFb.getWorkAttitude()* tmpduration;
        }
        double jobKnowleadge = sumJK/sumDuration;
        double workQuality = sumWQ/sumDuration;
        double cooperation = sumCo/sumDuration;
        double attendance = sumAt/sumDuration;
        double workAtitude = sumWA/sumDuration;
        double avrRating = (jobKnowleadge * 3 + workQuality *3 + cooperation * 2 + attendance + workAtitude)/10;
        averageRating.setJobKnowledge((double)Math.round(jobKnowleadge * 100)/100 );
        averageRating.setWorkQuality((double)Math.round(workQuality * 100)/100 );
        averageRating.setCooperation((double)Math.round(cooperation * 100)/100 );
        averageRating.setAttendance((double)Math.round(attendance * 100)/100 );
        averageRating.setWorkAttitude((double)Math.round(workAtitude * 100)/100 );
        averageRating.setAvgRating((double)Math.round(avrRating * 100)/100 );
        averageRatingRepository.save(averageRating);
        return "Success!!";
    }

    public String calculateRanking(int resourceId, Feedback feedback){
        HumanResource humanResource = humanResourceRepository.getById(resourceId);

        Job job = jobRepository.findById(feedback.getJobId());
        Contract contract = contractRepository.findById((int)job.getContractId());
        long duration = (contract.getEndDate() - contract.getStartDate())/86400000;
        if (duration <= 0){
            return "Sua contract startDate < endDate";
        }
        double experience = 0;
        double count = 0;
        int count2 = 0;
        List<SkillRequirements> skillRequirementsList = new ArrayList<>();
        List<ProjectRequirements> projectRequirementsList = projectRequirementRepository.findByProjectId(job.getProjectId());
        for (ProjectRequirements tmp: projectRequirementsList) {
            skillRequirementsList.addAll(tmp.getSkillRequirementsById());
        }
        for (SkillRequirements tmpSR: skillRequirementsList) {
            if(tmpSR.getExperience() < 20) {
                count2++;
                count += tmpSR.getExperience();
            }
        }
        if(count2 != 0) {
            experience = count / count2;
        }
        if(experience == 0){
            return "Check project requirement and skill requirement";
        }

        double jobScore = 1000 + (duration - 90) * 5 + (experience - 1) * 50;
        double humanScore = 900;
        if(humanResource.getAvgRating() != 0){
            humanScore = humanResource.getAvgRating();
        }
        float expect = expectScore(jobScore, humanScore);
        double actualScore = 0;
        double K = 10 + 20 * (3 - feedback.getRating());
        if(feedback.getRating() == 3){
            K = 10;
            actualScore = 0.5;
        }
        if (feedback.getRating() > 3){
            K = 10 + 20 * (feedback.getRating() - 3);
            actualScore = 1;
        }

        humanScore = humanScore + K * (actualScore - expect);
        int i = (int) Math.round(humanScore);
        humanResource.setAvgRating(i);

        humanResourceRepository.save(humanResource);
        return "Success";
    }

    public List<Project> loadAllProjectFeedBack(int userId){
        List<Project> projectList = projectRepository.findByUserIdAndProcessStatus(userId, Constant.ProjectProcess.FINISHED);
        List<Project> resultList = new ArrayList<>();
        for (Project tmp: projectList) {
            List<Job> jobList = jobRepository.findByProjectIdAndStatus(tmp.getId(), Constant.JobStatus.FINISHED);
            List<Job> resultJobList = new ArrayList<>();
            if(!jobList.isEmpty()) {
                for (Job jobtmp : jobList) {
                    if (jobtmp.getFeedbacksById().isEmpty()) {
                        resultJobList.add(jobtmp);
                    }
                }
                if (!resultJobList.isEmpty()) {
                    tmp.setJobsById(resultJobList);
                    resultList.add(tmp);
                }
            }
        }
        return resultList;
    }

    public List<Job> loadReoursceByProject(int projectId) throws Exception{
        Project project = projectRepository.findById(projectId);
        if(project == null){
            throw new NotFoundException("Project Not Found!");
        }
        if(project.getProcessStatus() != Constant.ProjectProcess.FINISHED){
            throw new Exception("Project not finish");
        }
        List<Job> jobList = jobRepository.findByProjectId(projectId);
        List<Job> resultList = new ArrayList<>();
        if(jobList == null){
           throw new NotFoundException("Project has no job");
        }
        for (Job tmp: jobList) {
            if(tmp.getFeedbacksById().isEmpty() && tmp.getStatus() == Constant.JobStatus.FINISHED){
                resultList.add(tmp);
            }
        }
        return resultList;
    }
    public float expectScore(double jobScore, double humanScore)
    {
        return (float) (1.0 * 1.0 / (1 + 1.0 * Math.pow(10, 1.0 * (jobScore - humanScore) / 400)));
    }
}
