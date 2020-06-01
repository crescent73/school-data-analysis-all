package com.crescent.controller;

import com.crescent.bean.CompetitionAnalysis;
import com.crescent.bean.CompetitionLevelAnalysis;
import com.crescent.bean.CompetitionRanking;
import com.crescent.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    @RequestMapping("/analysis")
    List<CompetitionAnalysis> competitionAnalysis() {
        return competitionService.competitionAnalysis();
    }

    @RequestMapping("/level_analysis")
    List<CompetitionLevelAnalysis> competitionLevelAnalysis() {
        return competitionService.competitionLevelAnalysis();
    }

    @RequestMapping("/ranking")
    List<CompetitionRanking> competitionRanking() {
        return competitionService.competitionRanking();
    }
}
