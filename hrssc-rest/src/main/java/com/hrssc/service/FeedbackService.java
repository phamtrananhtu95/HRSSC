package com.hrssc.service;

import com.hrssc.entities.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> loadAllFeedback(int humanResourceId);
    String addFeedback (Feedback feedback);
}
