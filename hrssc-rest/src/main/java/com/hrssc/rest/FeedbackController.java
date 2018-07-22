package com.hrssc.rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.FeedbackView;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.entities.Feedback;
import com.hrssc.entities.Project;
import com.hrssc.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hrssc.domain.dto.ResponseStatus;

import java.util.List;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @JsonView(HumanResourceView.history.class)
    @GetMapping("/loadAllFeedback/{humanresourceId}")
    public List<Feedback> loadAllFeedback(@PathVariable(value = "humanresourceId") int humanresourceId){
        return feedbackService.loadAllFeedback(humanresourceId);
    }

    @PostMapping(value = "/add")
    public ResponseStatus addFeedback(@RequestBody Feedback feedback) {
        ResponseStatus respon = new ResponseStatus(feedbackService.addFeedback(feedback));
        return respon;
    }

    @JsonView(FeedbackView.projectFeedback.class)
    @GetMapping(value = "/project-feedback/{userId}")
    public List<Project> loadAllProjectFeedback(@PathVariable(value = "userId") int userId){
        return feedbackService.loadAllProjectFeedBack(userId);
    }

}
