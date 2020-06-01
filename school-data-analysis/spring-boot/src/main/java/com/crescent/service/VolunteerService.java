package com.crescent.service;

import com.crescent.bean.VolunteerAcademyParticipationAnalysis;
import com.crescent.bean.VolunteerGradeAnalysis;
import com.crescent.bean.VolunteerGradeParticipationAnalysis;
import com.crescent.bean.VolunteerLevelAnalysis;

import java.util.List;

public interface VolunteerService {
    List<VolunteerAcademyParticipationAnalysis> volunteerAcademyParticipationAnalysis();
    List<VolunteerGradeAnalysis> volunteerGradeAnalysis();
    List<VolunteerGradeParticipationAnalysis> volunteerGradeParticipationAnalysis();
    List<VolunteerLevelAnalysis> volunteerLevelAnalysis();
}
