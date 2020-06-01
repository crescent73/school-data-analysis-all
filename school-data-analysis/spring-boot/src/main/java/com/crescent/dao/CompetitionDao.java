package com.crescent.dao;

import com.crescent.bean.CompetitionAnalysis;
import com.crescent.bean.CompetitionLevelAnalysis;
import com.crescent.bean.CompetitionRanking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompetitionDao {

    @Select("select * from competition_analysis")
    List<CompetitionAnalysis> competitionAnalysis();

    @Select("select * from competition_level_analysis")
    List<CompetitionLevelAnalysis> competitionLevelAnalysis();

    @Select("select * from competition_ranking")
    List<CompetitionRanking> competitionRanking();
}
