package com.hrssc.service;

import com.hrssc.entities.Feedback;
import com.hrssc.entities.Job;
import com.hrssc.entities.Project;

import java.util.List;

public interface FeedbackService {
    Feedback addFeedback (Feedback feedback) throws Exception;
    List<Feedback> loadAllFeedback(int resourceId);
    List<Project> loadAllProjectFeedBack(int userId);
    List<Job> loadReoursceByProject(int projectId) throws Exception;
    String calculateAvrRating(int resourceId, Feedback feedback);
    String calculateRanking(int resourceId, Feedback feedback);
    }
