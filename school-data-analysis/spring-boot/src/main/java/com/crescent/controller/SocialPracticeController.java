package com.crescent.controller;

import com.crescent.bean.SocialPracticeProjectAnalysis;
import com.crescent.bean.SocialPracticeTypeAnalysis;
import com.crescent.service.SocialPracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/social_practice")
public class SocialPracticeController {

    @Autowired
    private SocialPracticeService socialPracticeService;

    @RequestMapping("/project_analysis")
    List<SocialPracticeProjectAnalysis> socialPracticeProjectAnalysis() {
        return socialPracticeService.socialPracticeProjectAnalysis();
    }

    @RequestMapping("/type_analysis")
    List<SocialPracticeTypeAnalysis> socialPracticeTypeAnalysis() {
        return socialPracticeService.socialPracticeTypeAnalysis();
    }
}
