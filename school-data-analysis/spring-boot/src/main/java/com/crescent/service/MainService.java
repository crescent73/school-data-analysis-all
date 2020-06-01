package com.crescent.service;

import com.crescent.bean.BirthplaceAnalysis;
import com.crescent.bean.GenderAnalysis;
import com.crescent.bean.LevelAnalysis;
import com.crescent.bean.NationAnalysis;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MainService {
    List<NationAnalysis> nationAnalysis();
    List<BirthplaceAnalysis> birthplaceAnalysis();
    List<GenderAnalysis> genderAnalysis();
    List<LevelAnalysis> levelAnalysis();
}
