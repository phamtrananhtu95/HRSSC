package com.hrssc.service;

import com.hrssc.entities.Feedback;
import com.hrssc.entities.Job;
import com.hrssc.entities.Project;

import java.util.List;

public interface FeedbackService {
    String addFeedback (Feedback feedback);
    List<Feedback> loadAllFeedback(int resourceId);
    List<Project> loadAllProjectFeedBack(int userId);
    List<Job> loadReoursceByProject(int projectId) throws Exception;

    }
