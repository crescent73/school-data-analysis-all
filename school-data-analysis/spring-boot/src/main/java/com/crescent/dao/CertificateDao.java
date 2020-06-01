package com.crescent.dao;

import com.crescent.bean.CertificateAcademyAnalysis;
import com.crescent.bean.CertificateTypeAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CertificateDao {

    @Select("select * from certificate_academy_analysis order by grade")
    List<CertificateAcademyAnalysis> certificateAcademyAnalysis();

    @Select("select * from certificate_type_analysis order by count desc limit 10")
    List<CertificateTypeAnalysis> certificateTypeAnalysis();
}
