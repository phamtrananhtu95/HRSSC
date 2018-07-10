package com.hrssc.service.impl;

import com.hrssc.entities.Feedback;
import com.hrssc.repository.FeedbackRepository;
import com.hrssc.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    public List<Feedback> loadAllFeedback(int humanResourceId){
        List<Feedback> feedbackList = feedbackRepository.findByHumanResourceId(humanResourceId);

        return feedbackList;
    }

}
