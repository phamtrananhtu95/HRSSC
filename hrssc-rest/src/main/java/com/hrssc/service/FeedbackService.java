package com.hrssc.service;

import com.hrssc.entities.Feedback;

import java.util.List;

public interface FeedbackService {
    String addFeedback (Feedback feedback);
    List<Feedback> loadAllFeedback(int resourceId);
}
