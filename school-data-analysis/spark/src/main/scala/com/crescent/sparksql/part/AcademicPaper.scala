package com.crescent.sparksql.part

import org.apache.spark.sql.{SaveMode, SparkSession}

object AcademicPaper {

  /**
   * 学术论文部分数据分析
   * 1）热点话题分析
   * 2）论文独撰与合著比例
   * 3）与年级和学院关系
   * @param spark
   */
  def analysisAcademicPaper(spark:SparkSession):Unit = {
    spark.read.format("csv").option("header","true").load("in/学术论文.csv").createOrReplaceTempView("academic_paper")
    // 热点话题分析

    // 分析论文独撰与合著比例
    analysisAuthoringType(spark)

    // 与年级与学院的关系
    analysisAcademicPaperWithAcademy(spark)

  }

  private def analysisAuthoringType(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select authoring_type,count(*) count
        |from academic_paper
        |group by authoring_type
        |order by count
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "academic_authoring_type_analysis")
      .option("user", "root").option("password", "root").save()
  }

  private def analysisAcademicPaperWithAcademy(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select academy, count(*) count
        |from academic_paper
        |group by academy
        |order by count desc
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "academic_academy_analysis")
      .option("user", "root").option("password", "root").save()
  }

}
