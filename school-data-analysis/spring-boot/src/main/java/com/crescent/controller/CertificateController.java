package com.crescent.controller;

import com.crescent.bean.CertificateAcademyAnalysis;
import com.crescent.bean.CertificateTypeAnalysis;
import com.crescent.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @RequestMapping("/academy_analysis")
    List<CertificateAcademyAnalysis> certificateAcademyAnalysis() {
        return certificateService.certificateAcademyAnalysis();
    }

    @RequestMapping("/type_analysis")
    List<CertificateTypeAnalysis> certificateTypeAnalysis() {
        return certificateService.certificateTypeAnalysis();
    }
}
