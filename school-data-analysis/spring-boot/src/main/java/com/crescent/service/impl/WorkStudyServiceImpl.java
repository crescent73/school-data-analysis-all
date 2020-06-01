package com.crescent.service.impl;

import com.crescent.bean.WorkStudyJobAnalysis;
import com.crescent.bean.WorkStudyParticipationAnalysis;
import com.crescent.dao.WorkStudyDao;
import com.crescent.service.WorkStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkStudyServiceImpl implements WorkStudyService {

    @Autowired
    private WorkStudyDao workStudyDao;

    @Override
    public List<WorkStudyJobAnalysis> workStudyJobAnalysis() {
        return workStudyDao.workStudyJobAnalysis();
    }

    @Override
    public List<WorkStudyParticipationAnalysis> workStudyParticipationAnalysis() {
        return workStudyDao.workStudyParticipationAnalysis();
    }
}
