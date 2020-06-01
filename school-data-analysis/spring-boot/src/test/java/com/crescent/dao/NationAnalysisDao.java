package com.crescent.dao;

import com.crescent.bean.NationAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NationAnalysisDao {
    @Select("select * from nation_analysis")
    List<NationAnalysis> search();

    List<NationAnalysis> searchAll();

}
