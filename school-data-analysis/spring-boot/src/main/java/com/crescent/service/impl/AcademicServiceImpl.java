package com.crescent.service.impl;

import com.crescent.bean.AcademicAcademyAnalysis;
import com.crescent.bean.AcademicAuthoringTypeAnalysis;
import com.crescent.dao.AcademicDao;
import com.crescent.service.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicServiceImpl implements AcademicService {

    @Autowired
    private AcademicDao academicDao;

    @Override
    public List<AcademicAcademyAnalysis> academicAcademyAnalysis() {
        return academicDao.academicAcademyAnalysis();
    }

    @Override
    public List<AcademicAuthoringTypeAnalysis> academicAuthoringTypeAnalysis() {
        return academicDao.academicAuthoringTypeAnalysis();
    }
}
