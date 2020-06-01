package com.crescent.service.impl;

import com.crescent.bean.CertificateAcademyAnalysis;
import com.crescent.bean.CertificateTypeAnalysis;
import com.crescent.dao.CertificateDao;
import com.crescent.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateDao certificateDao;

    @Override
    public List<CertificateAcademyAnalysis> certificateAcademyAnalysis() {
        return certificateDao.certificateAcademyAnalysis();
    }

    @Override
    public List<CertificateTypeAnalysis> certificateTypeAnalysis() {
        return certificateDao.certificateTypeAnalysis();
    }
}
