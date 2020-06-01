package com.crescent.service.impl;

import com.crescent.bean.CompetitionAnalysis;
import com.crescent.bean.CompetitionLevelAnalysis;
import com.crescent.bean.CompetitionRanking;
import com.crescent.dao.CompetitionDao;
import com.crescent.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;

    @Override
    public List<CompetitionAnalysis> competitionAnalysis() {
        return competitionDao.competitionAnalysis();
    }

    @Override
    public List<CompetitionLevelAnalysis> competitionLevelAnalysis() {
        return competitionDao.competitionLevelAnalysis();
    }

    @Override
    public List<CompetitionRanking> competitionRanking() {
        return competitionDao.competitionRanking();
    }
}
