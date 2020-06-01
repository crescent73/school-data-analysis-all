package com.crescent.dao;

import com.crescent.bean.WorkStudyJobAnalysis;
import com.crescent.bean.WorkStudyParticipationAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WorkStudyDao {

    @Select("select * from work_study_job_analysis")
    List<WorkStudyJobAnalysis> workStudyJobAnalysis();

    @Select("select * from work_study_participation_analysis")
    List<WorkStudyParticipationAnalysis> workStudyParticipationAnalysis();
}
