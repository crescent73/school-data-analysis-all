package com.crescent.dao;

import com.crescent.bean.SocialPracticeProjectAnalysis;
import com.crescent.bean.SocialPracticeTypeAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SocialPracticeDao {

    @Select("select * from social_practice_project_analysis")
    List<SocialPracticeProjectAnalysis> socialPracticeProjectAnalysis();

    @Select("select * from social_practice_type_analysis")
    List<SocialPracticeTypeAnalysis> socialPracticeTypeAnalysis();
}
