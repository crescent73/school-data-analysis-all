package com.crescent.service;

import com.crescent.bean.CompetitionAnalysis;
import com.crescent.bean.CompetitionLevelAnalysis;
import com.crescent.bean.CompetitionRanking;

import java.util.List;

public interface CompetitionService {
    List<CompetitionAnalysis> competitionAnalysis();
    List<CompetitionLevelAnalysis> competitionLevelAnalysis();
    List<CompetitionRanking> competitionRanking();
}
