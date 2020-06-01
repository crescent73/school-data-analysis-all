package com.crescent.service.impl;

import com.crescent.bean.PatentAcademyAnalysis;
import com.crescent.bean.PatentTypeAnalysis;
import com.crescent.dao.PatentDao;
import com.crescent.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatentServiceImpl implements PatentService {

    @Autowired
    private PatentDao patentDao;
    @Override
    public List<PatentAcademyAnalysis> patentAcademyAnalysis() {
        return patentDao.patentAcademyAnalysis();
    }

    @Override
    public List<PatentTypeAnalysis> patentTypeAnalysis() {
        return patentDao.patentTypeAnalysis();
    }
}
