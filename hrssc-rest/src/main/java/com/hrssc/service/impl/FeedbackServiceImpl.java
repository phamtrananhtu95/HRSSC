package com.hrssc.service.impl;

import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<Feedback> loadAllFeedback(int humanResourceId){
        List<Feedback> feedbackList = feedbackRepository.findByHumanResourceId(humanResourceId);

        return feedbackList;
    }

    @Transactional
    public String addFeedback (Feedback feedback){
        User usercheck = userRepository.findById(feedback.getUserId());
        HumanResource resourceCheck = humanResourceRepository.getById(feedback.getHumanResourceId());
        if(usercheck == null || resourceCheck == null){
            return "Humanresource or User not exist!!";
        }

            Feedback newFeedback = new Feedback();
            newFeedback.setHumanResourceId(feedback.getHumanResourceId());
            newFeedback.setComment(feedback.getComment());
            newFeedback.setRating(feedback.getRating());
            newFeedback.setUserId(feedback.getUserId());
            long timeStamp = System.currentTimeMillis()/1000;
            newFeedback.setTimestamp(timeStamp);
            feedbackRepository.save(newFeedback);

            double count = 0;
            List<Feedback> feedbackList = feedbackRepository.findByHumanResourceId(feedback.getHumanResourceId());
            for (Feedback tmp : feedbackList) {
            count += tmp.getRating();
        }
            double avgRating = count/feedbackList.size();
            resourceCheck.setAvgRating(avgRating);

            humanResourceRepository.save(resourceCheck);
            return "Success!!";
    }

}
