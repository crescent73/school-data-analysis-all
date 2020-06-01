package com.crescent.service;

import com.crescent.bean.PatentAcademyAnalysis;
import com.crescent.bean.PatentTypeAnalysis;

import java.util.List;

public interface PatentService {
    List<PatentAcademyAnalysis> patentAcademyAnalysis();
    List<PatentTypeAnalysis> patentTypeAnalysis();
}
