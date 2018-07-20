package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.FeedbackService;
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


    public List<Feedback> loadAllFeedback(int resourceId){
        List<Job> jobList = jobRepository.findByHumanResourceIdAndStatus(resourceId, Constant.JobStatus.FINISHED);
        List<Feedback> resultList = new ArrayList<>();
        for (Job jobtmp: jobList) {
            List<Feedback> feedbackList = (List<Feedback>) jobtmp.getFeedbacksById();
            if(!feedbackList.isEmpty()){
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

        //tim cac job cua resource va loai bo cac job khong co feedback
        List<Job> humanResourceJobList = jobRepository.findByHumanResourceIdAndStatus(humanResource.getId(), Constant.JobStatus.FINISHED);
        List<Job> calcujobList = null;
        for (Job tmp: humanResourceJobList) {
            if(tmp.getFeedbacksById() != null);
            calcujobList.add(tmp);
        }

        //Tinh avgrating cho resource
        for (Job tmp : calcujobList) {
           Feedback tmpFb = feedbackRepository.findByJobId(tmp.getId());
        }




        return "Success!!";
    }

}
