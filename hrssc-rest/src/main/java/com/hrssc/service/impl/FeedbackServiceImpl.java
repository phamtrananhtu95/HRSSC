package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.FeedbackService;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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


    public List<Feedback> loadAllFeedback(int resourceId){
        List<Job> jobList = jobRepository.findByHumanResourceIdAndStatus(resourceId, Constant.JobStatus.FINISHED);
        List<Feedback> resultList = new ArrayList<>();
        for (Job jobtmp: jobList) {
            List<Feedback> feedbackList = (List<Feedback>) jobtmp.getFeedbacksById();
            if(!feedbackList.isEmpty()){
                long curentime = System.currentTimeMillis()/1000;
                jobtmp.setLeaveDate(curentime);
                long duration = (jobtmp.getLeaveDate() - jobtmp.getJoinedate())/86400;
                jobtmp.setJoinedate(duration);
                resultList.addAll(feedbackList);

            }
        }
        return resultList;
    }

    @Transactional
    public String addFeedback (Feedback feedback){
        Job checkjob = jobRepository.findById(feedback.getJobId());
        if(checkjob == null || checkjob.getStatus() != Constant.JobStatus.FINISHED){
            return "Job not exist or not yet finish!";
        }
        HumanResource humanResource = humanResourceRepository.getById(checkjob.getHumanResourceId());
        if (humanResource == null){
            return "Resource not exist!";
        }
        //Tinh rating +save feedback
        double rating = (feedback.getJobKnowledge()*3 + feedback.getWorkQuality()*3 + feedback.getCooperation()*2
                        +feedback.getAttendance() + feedback.getWorkAttitude())/10 ;
        feedback.setRating(rating);
        long timestamp = System.currentTimeMillis()/1000;
        feedback.setTimestamp(timestamp);
        feedbackRepository.save(feedback);


        //Tinh avgrating cho resource
        //tim cac job cua resource va loai bo cac job khong co feedback
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
        float sumRating = 0;
        for (Job tmp : calcujobList) {
           Feedback tmpFb = feedbackRepository.findByJobId(tmp.getId());
           long tmpduration = (tmp.getLeaveDate() - tmp.getJoinedate())/86400;
            sumDuration += tmpduration;
            sumJK += tmpFb.getJobKnowledge();
            sumWQ += tmpFb.getWorkQuality();
            sumCo += tmpFb.getCooperation();
            sumAt += tmpFb.getAttendance();
            sumWA += tmpFb.getWorkAttitude();
            sumRating += (float)tmpFb.getRating() * tmpduration;
        }
        int i = calcujobList.size();
        double jobKnowleadge = sumJK/i;
        double workQuality = sumWQ/i;
        double cooperation = sumCo/i;
        double attendance = sumAt/i;
        double workAtitude = sumWA/i;
        double avrRating = sumRating/sumDuration;
        averageRating.setJobKnowledge((double)Math.round(jobKnowleadge * 100)/100 );
        averageRating.setWorkQuality((double)Math.round(workQuality * 100)/100 );
        averageRating.setCooperation((double)Math.round(cooperation * 100)/100 );
        averageRating.setAttendance((double)Math.round(attendance * 100)/100 );
        averageRating.setWorkAttitude((double)Math.round(workAtitude * 100)/100 );
        averageRating.setAvgRating((double)Math.round(avrRating * 100)/100 );
        averageRatingRepository.save(averageRating);
        return "Success!!";
    }

    public List<Project> loadAllProjectFeedBack(int userId){
        List<Project> projectList = projectRepository.findByUserIdAndProcessStatus(userId, Constant.ProjectProcess.FINISHED);
        List<Project> resultList = new ArrayList<>();
        for (Project tmp: projectList) {
            List<Job> jobList = jobRepository.findByProjectId(tmp.getId());
            List<Job> resultJobList = new ArrayList<>();
            for (Job jobtmp: jobList) {
                if(jobtmp.getFeedbacksById().isEmpty()){
                    resultJobList.add(jobtmp);
                }
            }
            if (!resultJobList.isEmpty()){
                tmp.setJobsById(resultJobList);
                resultList.add(tmp);
            }
        }
        return resultList;
    }
}
