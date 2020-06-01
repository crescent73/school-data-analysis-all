package com.crescent.service.impl;

import com.crescent.bean.BirthplaceAnalysis;
import com.crescent.bean.GenderAnalysis;
import com.crescent.bean.LevelAnalysis;
import com.crescent.bean.NationAnalysis;
import com.crescent.dao.MainDao;
import com.crescent.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainDao mainDao;

    @Override
    public List<NationAnalysis> nationAnalysis() {
        return mainDao.nationAnalysis();
    }

    @Override
    public List<BirthplaceAnalysis> birthplaceAnalysis() {
        return mainDao.birthplaceAnalysis();
    }

    @Override
    public List<GenderAnalysis> genderAnalysis() {
        return mainDao.genderAnalysis();
    }

    @Override
    public List<LevelAnalysis> levelAnalysis() {
        return mainDao.levelAnalysis();
    }
}
