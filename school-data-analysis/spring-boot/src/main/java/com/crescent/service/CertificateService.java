package com.crescent.service;

import com.crescent.bean.CertificateAcademyAnalysis;
import com.crescent.bean.CertificateTypeAnalysis;

import java.util.List;

public interface CertificateService {
    List<CertificateAcademyAnalysis> certificateAcademyAnalysis();
    List<CertificateTypeAnalysis> certificateTypeAnalysis();
}
