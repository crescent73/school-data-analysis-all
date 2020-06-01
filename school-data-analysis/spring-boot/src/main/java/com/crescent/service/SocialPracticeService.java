package com.crescent.service;

import com.crescent.bean.SocialPracticeProjectAnalysis;
import com.crescent.bean.SocialPracticeTypeAnalysis;

import java.util.List;

public interface SocialPracticeService {
    List<SocialPracticeProjectAnalysis> socialPracticeProjectAnalysis();
    List<SocialPracticeTypeAnalysis> socialPracticeTypeAnalysis();
}
