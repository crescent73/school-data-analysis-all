package com.crescent.dao;

import com.crescent.bean.AcademicAcademyAnalysis;
import com.crescent.bean.AcademicAuthoringTypeAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AcademicDao {
    @Select("select * from academic_academy_analysis")
    List<AcademicAcademyAnalysis> academicAcademyAnalysis();

    @Select("select * from academic_authoring_type_analysis")
    List<AcademicAuthoringTypeAnalysis> academicAuthoringTypeAnalysis();
}
