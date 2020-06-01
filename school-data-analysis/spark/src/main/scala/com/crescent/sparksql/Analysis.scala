package com.crescent.sparksql

import com.crescent.sparksql.part.{AcademicPaper, Competition, Main, Patent, SkillCertificate, SocialPractice, VolunteerService, WorkStudy}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{SaveMode, SparkSession}

object Analysis {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("analysis")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    // 概览数据分析
    Main.analysisMain(spark)
    // 竞赛数据分析
    Competition.analysisCompetition(spark)
    // 学术论文分许
    AcademicPaper.analysisAcademicPaper(spark)
    // 专利分析
    Patent.analysisPatent(spark)
    // 技能证书分析
    SkillCertificate.analysisSkillCertificate(spark)
    // 社会实践分析
    SocialPractice.analysisSocialPractice(spark)
    // 志愿服务分析
    VolunteerService.analysisVolunteerService(spark)
    // 勤工助学分析
    WorkStudy.analysisWorkStudy(spark)
  }

}
