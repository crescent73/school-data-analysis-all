package com.crescent.controller;

import com.crescent.bean.WorkStudyJobAnalysis;
import com.crescent.bean.WorkStudyParticipationAnalysis;
import com.crescent.service.WorkStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/work_study")
public class WorkStudyController {
    @Autowired
    private WorkStudyService workStudyService;

    @RequestMapping("/job_analysis")
    List<WorkStudyJobAnalysis> workStudyJobAnalysis() {
        return workStudyService.workStudyJobAnalysis();
    }

    @RequestMapping("/participation_analysis")
    List<WorkStudyParticipationAnalysis> workStudyParticipationAnalysis() {
        return workStudyService.workStudyParticipationAnalysis();
    }
}
