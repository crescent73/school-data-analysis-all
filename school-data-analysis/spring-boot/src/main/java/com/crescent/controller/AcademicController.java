package com.crescent.controller;

import com.crescent.bean.AcademicAcademyAnalysis;
import com.crescent.bean.AcademicAuthoringTypeAnalysis;
import com.crescent.service.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/academic")
public class AcademicController {

    @Autowired
    private AcademicService academicService;

    @RequestMapping("/academy_analysis")
    List<AcademicAcademyAnalysis> academicAcademyAnalysis() {
        return academicService.academicAcademyAnalysis();
    }

    @RequestMapping("/authoring_type_analysis")
    List<AcademicAuthoringTypeAnalysis> academicAuthoringTypeAnalysis() {
        return academicService.academicAuthoringTypeAnalysis();
    }
}
