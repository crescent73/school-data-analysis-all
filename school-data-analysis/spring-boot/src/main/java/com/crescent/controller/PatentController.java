package com.crescent.controller;

import com.crescent.bean.PatentAcademyAnalysis;
import com.crescent.bean.PatentTypeAnalysis;
import com.crescent.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patent")
public class PatentController {
    @Autowired
    private PatentService patentService;

    @RequestMapping("/academy_analysis")
    List<PatentAcademyAnalysis> patentAcademyAnalysis() {
        return patentService.patentAcademyAnalysis();
    }

    @RequestMapping("/type_analysis")
    List<PatentTypeAnalysis> patentTypeAnalysis() {
        return patentService.patentTypeAnalysis();
    }
}
