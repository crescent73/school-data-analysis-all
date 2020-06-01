package com.crescent.dao;

import com.crescent.bean.VolunteerAcademyParticipationAnalysis;
import com.crescent.bean.VolunteerGradeAnalysis;
import com.crescent.bean.VolunteerGradeParticipationAnalysis;
import com.crescent.bean.VolunteerLevelAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VolunteerDao {
    @Select("select * from volunteer_academy_participation_analysis")
    List<VolunteerAcademyParticipationAnalysis> volunteerAcademyParticipationAnalysis();

    @Select("select * from volunteer_grade_analysis")
    List<VolunteerGradeAnalysis> volunteerGradeAnalysis();

    @Select("select * from volunteer_grade_participation_analysis")
    List<VolunteerGradeParticipationAnalysis> volunteerGradeParticipationAnalysis();

    @Select("select * from volunteer_level_analysis")
    List<VolunteerLevelAnalysis> volunteerLevelAnalysis();
}
