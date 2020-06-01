package com.crescent.service.impl;

import com.crescent.bean.SocialPracticeProjectAnalysis;
import com.crescent.bean.SocialPracticeTypeAnalysis;
import com.crescent.dao.SocialPracticeDao;
import com.crescent.service.SocialPracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialPracticeServiceImpl implements SocialPracticeService {

    @Autowired
    private SocialPracticeDao socialPracticeDao;

    @Override
    public List<SocialPracticeProjectAnalysis> socialPracticeProjectAnalysis() {
        return socialPracticeDao.socialPracticeProjectAnalysis();
    }

    @Override
    public List<SocialPracticeTypeAnalysis> socialPracticeTypeAnalysis() {
        return socialPracticeDao.socialPracticeTypeAnalysis();
    }
}
