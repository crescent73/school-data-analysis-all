package com.crescent.service.impl;

import com.crescent.bean.VolunteerAcademyParticipationAnalysis;
import com.crescent.bean.VolunteerGradeAnalysis;
import com.crescent.bean.VolunteerGradeParticipationAnalysis;
import com.crescent.bean.VolunteerLevelAnalysis;
import com.crescent.dao.VolunteerDao;
import com.crescent.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerDao volunteerDao;

    @Override
    public List<VolunteerAcademyParticipationAnalysis> volunteerAcademyParticipationAnalysis() {
        return volunteerDao.volunteerAcademyParticipationAnalysis();
    }

    @Override
    public List<VolunteerGradeAnalysis> volunteerGradeAnalysis() {
        return volunteerDao.volunteerGradeAnalysis();
    }

    @Override
    public List<VolunteerGradeParticipationAnalysis> volunteerGradeParticipationAnalysis() {
        return volunteerDao.volunteerGradeParticipationAnalysis();
    }

    @Override
    public List<VolunteerLevelAnalysis> volunteerLevelAnalysis() {
        return volunteerDao.volunteerLevelAnalysis();
    }
}
