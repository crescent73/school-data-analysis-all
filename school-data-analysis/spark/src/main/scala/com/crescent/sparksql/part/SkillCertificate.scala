package com.crescent.sparksql.part

import org.apache.spark.sql.{SaveMode, SparkSession}

object SkillCertificate {

  /**
   * 分析技能证书数据
   * 1）证书类型 & 等级 & 具体小类
   * 2）证书与年级学院关系
   * @param spark
   */
  def analysisSkillCertificate(spark:SparkSession):Unit = {
    spark.read.format("csv").option("header","true").load("in/技能证书.csv").createOrReplaceTempView("certificate")
    // 证书类型，等级，和各类分析
//    analysisCertificateType(spark)

    // 证书与年级学院的关系
    analysisCertificateAndAcademy(spark)


  }

  private def analysisCertificateType(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select skill_type,skill_level,award_name,count(*) count
        |from certificate
        |group by skill_type,skill_level,award_name
        |order by skill_type,skill_level
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "certificate_type_analysis")
      .option("user", "root").option("password", "root").save()

  }

  private def analysisCertificateAndAcademy(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select academy,substr(class,INSTR(class,'1'),2) as grade,count(*) count
        |from certificate
        |group by academy,grade
        |order by academy,grade
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "certificate_academy_analysis")
      .option("user", "root").option("password", "root").save()
  }


}
