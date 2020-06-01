package com.crescent.dao;

import com.crescent.bean.BirthplaceAnalysis;
import com.crescent.bean.GenderAnalysis;
import com.crescent.bean.LevelAnalysis;
import com.crescent.bean.NationAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MainDao {
    @Select("select * from nation_analysis order by count desc limit 10")
    List<NationAnalysis> nationAnalysis();

    @Select("select * from birthplace_analysis")
    List<BirthplaceAnalysis> birthplaceAnalysis();

    @Select("select * from gender_analysis")
    List<GenderAnalysis> genderAnalysis();

    @Select("select * from level_analysis")
    List<LevelAnalysis> levelAnalysis();
}
