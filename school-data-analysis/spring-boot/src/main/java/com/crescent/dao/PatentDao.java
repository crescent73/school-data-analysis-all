package com.crescent.dao;

import com.crescent.bean.PatentAcademyAnalysis;
import com.crescent.bean.PatentTypeAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Repository
@Mapper
public interface PatentDao {

    @Select("select * from patent_academy_analysis")
    List<PatentAcademyAnalysis> patentAcademyAnalysis();

    @Select("select * from patent_type_analysis")
    List<PatentTypeAnalysis> patentTypeAnalysis();
}
