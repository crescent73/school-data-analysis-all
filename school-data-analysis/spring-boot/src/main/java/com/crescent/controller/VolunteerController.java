package com.crescent.controller;

import com.crescent.bean.VolunteerAcademyParticipationAnalysis;
import com.crescent.bean.VolunteerGradeAnalysis;
import com.crescent.bean.VolunteerGradeParticipationAnalysis;
import com.crescent.bean.VolunteerLevelAnalysis;
import com.crescent.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @RequestMapping("/academy_participation_analysis")
    List<VolunteerAcademyParticipationAnalysis> volunteerAcademyParticipationAnalysis() {
        return volunteerService.volunteerAcademyParticipationAnalysis();
    }

    @RequestMapping("/grade_analysis")
    List<VolunteerGradeAnalysis> volunteerGradeAnalysis() {
        return volunteerService.volunteerGradeAnalysis();
    }

    @RequestMapping("/grade_participation_analysis")
    List<VolunteerGradeParticipationAnalysis> volunteerGradeParticipationAnalysis() {
        return volunteerService.volunteerGradeParticipationAnalysis();
    }

    @RequestMapping("/level_analysis")
    List<VolunteerLevelAnalysis> volunteerLevelAnalysis() {
        return volunteerService.volunteerLevelAnalysis();
    }
}
