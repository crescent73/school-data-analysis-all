package com.crescent.service;

import com.crescent.bean.AcademicAcademyAnalysis;
import com.crescent.bean.AcademicAuthoringTypeAnalysis;

import java.util.List;

public interface AcademicService {
    List<AcademicAcademyAnalysis> academicAcademyAnalysis();
    List<AcademicAuthoringTypeAnalysis> academicAuthoringTypeAnalysis();
}
