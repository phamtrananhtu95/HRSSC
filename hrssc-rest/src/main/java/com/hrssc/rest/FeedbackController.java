package com.hrssc.rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.FeedbackView;
import com.hrssc.entities.Feedback;
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

    @JsonView(FeedbackView.loadAllview.class)
    @GetMapping(value = "/loadAll/{humanResourceId}")
    public List<Feedback> loadAllFeedback(@PathVariable(value = "humanResourceId") int humanResourceId){
        return feedbackService.loadAllFeedback(humanResourceId);
    }

    @PostMapping(value = "/add")
    public ResponseStatus addFeedback(@RequestBody Feedback feedback) {
        ResponseStatus respon = new ResponseStatus(feedbackService.addFeedback(feedback));
        return respon;
    }
}
