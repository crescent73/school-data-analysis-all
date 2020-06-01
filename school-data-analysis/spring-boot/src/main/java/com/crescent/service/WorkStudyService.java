package com.crescent.service;

import com.crescent.bean.WorkStudyJobAnalysis;
import com.crescent.bean.WorkStudyParticipationAnalysis;

import java.util.List;

public interface WorkStudyService {
    List<WorkStudyJobAnalysis> workStudyJobAnalysis();
    List<WorkStudyParticipationAnalysis> workStudyParticipationAnalysis();
}
